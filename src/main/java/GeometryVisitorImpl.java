import compile.context.SymbolTable;
import org.antlr.v4.runtime.*;
import compile.translation.NodeAttributes;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import runtime.commands.*;
import runtime.function.CompiledFunction;
import types.BaseType;
import types.FunctionType;
import types.Type;

import java.util.*;
import java.util.stream.Stream;

import static compile.translation.CompileErrors.*;

public class GeometryVisitorImpl implements GeometryVisitor<NodeAttributes> {
    private SymbolTable symbolTable;

    public GeometryVisitorImpl(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public GeometryVisitorImpl() {
        this.symbolTable = new SymbolTable(null);
    }

    @Override
    public NodeAttributes visitProgram(GeometryParser.ProgramContext ctx) {
        List<NodeAttributes> stms = ctx.stm().stream()
                .map(this::visit)
                .toList();
        List<String> errors = getErrors(stms);
        if (!errors.isEmpty())
            return NodeAttributes.fromErrors(errors);

        return NodeAttributes.fromCode(getCode(stms));
    }

    @Override
    public NodeAttributes visitStm(GeometryParser.StmContext ctx) {
        if (ctx.varDecl() != null) {
            return visit(ctx.varDecl());
        } else if (ctx.funcDecl() != null) {
            return visit(ctx.funcDecl());
        } else if (ctx.showStm() != null) {
            return visit(ctx.showStm());
        } else {
            return visit(ctx.functionCall());
        }
    }

    @Override
    public NodeAttributes visitVarDecl(GeometryParser.VarDeclContext ctx) {
        Type type = visit(ctx.type()).getType();
        String id = ctx.ID().getText();
        NodeAttributes expr = visit(ctx.expr());

        if (!expr.getErrors().isEmpty()) {
            return NodeAttributes.fromErrors(expr.getErrors());
        }

        if(symbolTable.contains(id)){
            String error = idAlreadyExists(getLine(ctx), getCh(ctx), id);
            return NodeAttributes.fromError(error);
        }

        List<Command> exprCode = new ArrayList<>(expr.getCode());
        String exprVar = expr.getVarName();
        Type exprType = expr.getType();

        NodeAttributes assignment = addAssignment(type, id, exprType, exprVar, ctx);
        if(!assignment.getErrors().isEmpty()){
            return NodeAttributes.fromErrors(assignment.getErrors());
        }
        symbolTable.add(id, type);
        exprCode.addAll(assignment.getCode());
        if(isFigureType(type)){
            exprCode.addLast(new AddFigureNameCommand(id, id));
        }
        return NodeAttributes.fromCode(exprCode);
    }

    @Override
    public NodeAttributes visitFuncDecl(GeometryParser.FuncDeclContext ctx) {
        String name = ctx.ID().getText();

        if(symbolTable.contains(name)){
            String error = idAlreadyExists(getLine(ctx), getCh(ctx), name);
            return NodeAttributes.fromError(error);
        }

        Type returnType = visit(ctx.type()).getType();

        symbolTable = new SymbolTable(symbolTable);
        List<NodeAttributes> params;
        if(ctx.param() == null){
            params = new ArrayList<>();
        } else {
            params = ctx.param().stream()
                    .map(this::visit)
                    .toList();
        }
        List<String> errors = getErrors(params);
        if (!errors.isEmpty()) {
            return NodeAttributes.fromErrors(errors);
        }

        NodeAttributes prog = visit(ctx.funcProgram());
        symbolTable = symbolTable.getOuter();

        if(!prog.getErrors().isEmpty()) {
            return NodeAttributes.fromErrors(prog.getErrors());
        }

        List<Command> code = new ArrayList<>(prog.getCode());
        NodeAttributes returnCopy = addAssignment(returnType, randName(),
                prog.getType(), prog.getVarName(), ctx);
        if(!returnCopy.getErrors().isEmpty()) {
            return NodeAttributes.fromErrors(returnCopy.getErrors());
        }
        code.addAll(returnCopy.getCode());

        List<String> paramNames = params.stream().map(NodeAttributes::getVarName).toList();
        List<Type> paramTypes = params.stream().map(NodeAttributes::getType).toList();
        CompiledFunction func = new CompiledFunction(paramNames, code, returnCopy.getVarName());
        FunctionType funcType = new FunctionType(returnType, paramTypes);
        symbolTable.add(name, funcType);
        Command addFunc = new AddFunctionCommand(name, func);

        return NodeAttributes.fromCode(addFunc);
    }

    @Override
    public NodeAttributes visitShowStm(GeometryParser.ShowStmContext ctx) {
        NodeAttributes expr = visit(ctx.expr());
        if (!expr.getErrors().isEmpty()) {
            return NodeAttributes.fromErrors(expr.getErrors());
        }
        if(!isFigureType(expr.getType())) {
            String error = figureShowError(getLine(ctx), getCh(ctx), expr.getType());
            return NodeAttributes.fromError(error);
        }
        List<Command> code = new ArrayList<>(expr.getCode());
        code.addLast(new ShowCommand(expr.getVarName()));
        return NodeAttributes.fromCode(code);
    }

    @Override
    public NodeAttributes visitParam(GeometryParser.ParamContext ctx) {
        String id = ctx.ID().getText();
        Type type = visit(ctx.type()).getType();
        if(symbolTable.contains(id)){
            String error = idAlreadyExists(getLine(ctx), getCh(ctx), id);
            return NodeAttributes.fromError(error);
        } else {
            symbolTable.add(id, type);
            return NodeAttributes.builder()
                    .varName(id)
                    .type(type)
                    .build();
        }
    }

    @Override
    public NodeAttributes visitFuncProgram(GeometryParser.FuncProgramContext ctx) {
        List<NodeAttributes> stms = new ArrayList<>(ctx.stm().stream()
                .map(this::visit)
                .toList());
        NodeAttributes returnStm = visit(ctx.returnStm());
        stms.addLast(returnStm);

        List<String> errors = getErrors(stms);
        if (!errors.isEmpty()) {
            return NodeAttributes.fromErrors(errors);
        }

        List<Command> code = getCode(stms);
        return NodeAttributes.builder()
                .code(code)
                .varName(returnStm.getVarName())
                .type(returnStm.getType())
                .build();
    }

    @Override
    public NodeAttributes visitReturnStm(GeometryParser.ReturnStmContext ctx) {
        NodeAttributes expr = visit(ctx.expr());
        if (!expr.getErrors().isEmpty())
            return NodeAttributes.fromErrors(expr.getErrors());
        String resultVar = randName();
        List<Command> code = new ArrayList<>(expr.getCode());
        code.addLast(new ReturnCommand(resultVar, expr.getVarName()));
        return NodeAttributes.builder()
                .code(code)
                .varName(resultVar)
                .type(expr.getType())
                .build();
    }

    @Override
    public NodeAttributes visitExpr(GeometryParser.ExprContext ctx) {
        if(ctx.expr() != null) {
            return visit(ctx.expr());
        } else if (ctx.functionCall() != null) {
            return visit(ctx.functionCall());
        } else if (ctx.literal() != null) {
            return visit(ctx.literal());
        } else {
            String id = ctx.ID().getText();
            if(!symbolTable.contains(id)){
                String error = idNotDeclared(getLine(ctx), getCh(ctx), id);
                return NodeAttributes.fromError(error);
            } else {
                return NodeAttributes.builder()
                        .varName(id)
                        .type(symbolTable.get(id))
                        .build();
            }
        }
    }

    @Override
    public NodeAttributes visitFunctionCall(GeometryParser.FunctionCallContext ctx) {
        String name = ctx.ID().getText();
        if(!symbolTable.contains(name)){
            String error = idNotDeclared(getLine(ctx), getCh(ctx), name);
            return NodeAttributes.fromError(error);
        }

        Type type = symbolTable.get(name);
        if (!(type instanceof FunctionType signature)) {
            String error = calledNotAFunction(getLine(ctx), getCh(ctx), name);
            return NodeAttributes.fromError(error);
        }

        List<NodeAttributes> args;
        if(ctx.expr() == null){
            args = new ArrayList<>();
        } else {
            args = ctx.expr().stream()
                    .map(this::visit)
                    .toList();
        }

        List<String> errors = getErrors(args);
        if (!errors.isEmpty()) {
            return NodeAttributes.fromErrors(errors);
        }

        if (signature.getAccepts().size() != args.size()) {
            String error = wrongNumberOfArgs(getLine(ctx), getCh(ctx), name);
            return NodeAttributes.fromError(error);
        }
        List<NodeAttributes> copiedArgs = new ArrayList<>();
        for (int i = 0; i < args.size(); i++) {
            Type paramType = signature.getAccepts().get(i);
            String argName = args.get(i).getVarName();
            Type argType = args.get(i).getType();
            NodeAttributes copied = addAssignment(paramType, randName(), argType, argName, ctx);
            copiedArgs.add(copied);
        }

        errors = getErrors(copiedArgs);
        if (!errors.isEmpty()) {
            return NodeAttributes.fromErrors(errors);
        }

        List<Command> code = new ArrayList<>(getCode(args));
        code.addAll(getCode(copiedArgs));

        List<String> copiedArgNames = copiedArgs.stream().map(NodeAttributes::getVarName).toList();
        String returnTo = randName();
        code.addLast(new CallFunctionCommand(name, copiedArgNames, returnTo));

        return NodeAttributes.builder()
                .code(code)
                .varName(returnTo)
                .type(signature.getReturns())
                .build();
    }

    @Override
    public NodeAttributes visitType(GeometryParser.TypeContext ctx) {
        if (ctx.numericType() != null) {
            return visit(ctx.numericType());
        } else {
            return visit(ctx.geometricType());
        }
    }

    @Override
    public NodeAttributes visitNumericType(GeometryParser.NumericTypeContext ctx) {
        Type type;
        if (ctx.INT() != null)
            type = BaseType.INT;
        else
            type = BaseType.DOUBLE;
        return NodeAttributes.builder().type(type).build();
    }

    @Override
    public NodeAttributes visitGeometricType(GeometryParser.GeometricTypeContext ctx) {
        Type type;
        if (ctx.POINT() != null)
            type = BaseType.POINT;
        else if (ctx.SEGMENT() != null)
            type = BaseType.SEGMENT;
        else if (ctx.LINE() != null)
            type = BaseType.LINE;
        else if (ctx.QUAD() != null)
            type = BaseType.QUAD;
        else if (ctx.RHOMB() != null)
            type = BaseType.RHOMB;
        else if (ctx.TRAPEZ() != null)
            type = BaseType.TRAPEZ;
        else if (ctx.PARAL() != null)
            type = BaseType.PARAL;
        else if (ctx.RECT() != null)
            type = BaseType.RECT;
        else
            type = BaseType.SQUARE;
        return NodeAttributes.builder().type(type).build();
    }

    @Override
    public NodeAttributes visitLiteral(GeometryParser.LiteralContext ctx) {
        Type type;
        Object val;
        if (ctx.INT_CONST() != null) {
            val = Integer.parseInt(ctx.INT_CONST().getText());
            type = BaseType.INT;
        } else {
            val = Double.parseDouble(ctx.DOUBLE_CONST().getText());
            type = BaseType.DOUBLE;
        }
        String name = randName();
        return NodeAttributes.builder()
                .varName(name)
                .type(type)
                .code(List.of(new AddVariableCommand(name, val)))
                .build();
    }

    @Override
    public NodeAttributes visit(ParseTree parseTree) {
        if (parseTree == null)
            return null;
        return parseTree.accept(this);
    }

    @Override
    public NodeAttributes visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public NodeAttributes visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public NodeAttributes visitErrorNode(ErrorNode errorNode) {
        Token t = errorNode.getSymbol();
        String error = syntaxError(t.getLine(), t.getCharPositionInLine(), t.getText());
        return NodeAttributes.fromError(error);
    }

    private String randName() {
        return UUID.randomUUID().toString();
    }

    private int getLine(ParserRuleContext ctx){
        return ctx.getStart().getLine();
    }

    private int getCh(ParserRuleContext ctx){
        return ctx.getStart().getCharPositionInLine();
    }

    private List<String> getErrors(List<NodeAttributes> nodes) {
        return nodes.stream()
                .flatMap(c -> {
                    if(c.getErrors() != null)
                        return c.getErrors().stream();
                    else
                        return Stream.empty();
                })
                .toList();
    }

    private List<Command> getCode(List<NodeAttributes> nodes) {
        return nodes.stream()
                .flatMap(c -> {
                    if(c.getCode() != null)
                        return c.getCode().stream();
                    else
                        return Stream.empty();
                })
                .toList();
    }

    private NodeAttributes addAssignment(Type typeTo, String nameTo, Type typeFrom, String nameFrom, ParserRuleContext ctx) {
        List<Command> code = new ArrayList<>();
        if (typeTo != typeFrom) {
            if (typeFrom.canConvertTo(typeTo)) {
                String newFrom = randName();
                code.addLast(new ConvertTypeCommand(typeTo, newFrom, typeFrom, nameFrom));
                nameFrom = newFrom;
            } else {
                String error = typeConversionError(getLine(ctx), getCh(ctx), typeTo, typeFrom);
                return NodeAttributes.fromError(error);
            }
        }
        Command assignment = new AssignCommand(nameTo, nameFrom);
        code.addLast(assignment);
        return NodeAttributes.builder()
                .code(code)
                .varName(nameTo)
                .build();
    }

    private boolean isFigureType(Type type) {
        Set<Type> FIGURE_TYPES = Set.of(
                BaseType.POINT, BaseType.SEGMENT, BaseType.LINE, BaseType.QUAD,
                BaseType.PARAL, BaseType.TRAPEZ, BaseType.RHOMB, BaseType.RECT,
                BaseType.SQUARE
        );
        return FIGURE_TYPES.contains(type);
    }
}

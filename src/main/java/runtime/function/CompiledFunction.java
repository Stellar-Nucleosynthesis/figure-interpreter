package runtime.function;

import runtime.commands.Command;
import runtime.environment.ExecutionContext;
import figures.Figure;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompiledFunction implements Function {
    private final List<String> params;
    private final List<Command> commands;
    private final String returnValName;

    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        if (arguments.size() != params.size())
            throw new IllegalArgumentException("Number of parameters and passed arguments don't match");
        ExecutionContext context = new ExecutionContext(outerContext);
        for(int i = 0; i < params.size(); i++) {
            String name = params.get(i);
            Object val = arguments.get(i);
            context.setVar(name, val);
        }
        for(Command command : commands) {
            command.execute(context);
        }
        for(Figure figure : context.getShowList().getFigures()) {
            outerContext.showFigure(figure);
        }
        return context.getVar(returnValName);
    }
}

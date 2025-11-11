package builtIns.constructors;

import compile.context.SymbolTable;
import types.BaseType;
import types.FunctionType;
import types.Type;

import java.util.List;

public class ConstructorSymbolTable extends SymbolTable {
    public ConstructorSymbolTable(SymbolTable outer) {
        super(outer);
        add("ТОЧКА_ВІД", new FunctionType(BaseType.POINT, List.of(BaseType.DOUBLE,  BaseType.DOUBLE)));
        add("ВІДРІЗОК_ВІД", new FunctionType(BaseType.SEGMENT, List.of(BaseType.POINT,  BaseType.POINT)));
        add("ЛІНІЯ_ВІД", new FunctionType(BaseType.LINE, List.of(BaseType.DOUBLE,  BaseType.DOUBLE)));
        List<Type> fourPoints = List.of(BaseType.POINT, BaseType.POINT, BaseType.POINT, BaseType.POINT);
        add("ЧОТИРИКУТНИК_ВІД", new FunctionType(BaseType.QUAD, fourPoints));
        add("РОМБ_ВІД", new FunctionType(BaseType.RHOMB, fourPoints));
        add("ТРАПЕЦІЯ_ВІД", new FunctionType(BaseType.TRAPEZ, fourPoints));
        add("ПАРАЛЕЛОГРАМ_ВІД", new FunctionType(BaseType.PARAL, fourPoints));
        add("ПРЯМОКУТНИК_ВІД", new FunctionType(BaseType.RECT, fourPoints));
        add("КВАДРАТ_ВІД", new FunctionType(BaseType.SQUARE, fourPoints));
    }
}

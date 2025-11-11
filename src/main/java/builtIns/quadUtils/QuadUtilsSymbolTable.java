package builtIns.quadUtils;

import compile.context.SymbolTable;
import types.BaseType;
import types.FunctionType;

import java.util.List;

public class QuadUtilsSymbolTable extends SymbolTable {
    public QuadUtilsSymbolTable(SymbolTable outer) {
        super(outer);
        FunctionType returnsPoint = new FunctionType(BaseType.POINT, List.of(BaseType.QUAD));
        add("ПЕРША_ВЕРШИНА", returnsPoint);
        add("ДРУГА_ВЕРШИНА",  returnsPoint);
        add("ТРЕТЯ_ВЕРШИНА", returnsPoint);
        add("ЧЕТВЕРТА_ВЕРШИНА",  returnsPoint);
        FunctionType returnsSegment = new FunctionType(BaseType.SEGMENT, List.of(BaseType.QUAD));
        add("ПЕРША_СТОРОНА", returnsSegment);
        add("ДРУГА_СТОРОНА", returnsSegment);
        add("ТРЕТЯ_СТОРОНА", returnsSegment);
        add("ЧЕТВЕРТА_СТОРОНА", returnsSegment);

        FunctionType returnsDouble = new FunctionType(BaseType.DOUBLE, List.of(BaseType.QUAD));
        add("ПЕРИМЕТР", returnsDouble);
        add("ПЛОЩА", returnsDouble);
    }
}

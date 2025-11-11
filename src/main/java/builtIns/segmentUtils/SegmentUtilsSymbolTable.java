package builtIns.segmentUtils;

import compile.context.SymbolTable;
import types.BaseType;
import types.FunctionType;

import java.util.List;

public class SegmentUtilsSymbolTable extends SymbolTable {
    public SegmentUtilsSymbolTable(SymbolTable outer) {
        super(outer);
        add("ПЕРША_ТОЧКА", new FunctionType(BaseType.SEGMENT, List.of(BaseType.POINT)));
        add("ДРУГА_ТОЧКА", new FunctionType(BaseType.SEGMENT, List.of(BaseType.POINT)));
        add("НАХИЛ", new FunctionType(BaseType.LINE, List.of(BaseType.DOUBLE)));
        add("ЗМІЩЕННЯ", new FunctionType(BaseType.LINE, List.of(BaseType.DOUBLE)));

        add("ВИСОТА_НА_ПРЯМУ", new FunctionType(BaseType.SEGMENT, List.of(BaseType.LINE, BaseType.POINT)));
        add("ПРЯМА_З_ВІДРІЗКА", new FunctionType(BaseType.LINE, List.of(BaseType.SEGMENT)));
        add("ДОВЖИНА_ВІДРІЗКА", new FunctionType(BaseType.DOUBLE, List.of(BaseType.SEGMENT)));
        add("СЕРЕДИНА_ВІДРІЗКА", new FunctionType(BaseType.POINT, List.of(BaseType.SEGMENT)));
    }
}

package builtIns.io;

import compile.context.SymbolTable;
import types.BaseType;
import types.FunctionType;

import java.util.List;

public class IoSymbolTable extends SymbolTable {
    public IoSymbolTable(SymbolTable outer) {
        super(outer);
        add("ВИВЕСТИ_ЦІЛЕ", new FunctionType(BaseType.INT, List.of(BaseType.INT)));
        add("ВИВЕСТИ_ДІЙСНЕ", new FunctionType(BaseType.INT, List.of(BaseType.DOUBLE)));
    }
}

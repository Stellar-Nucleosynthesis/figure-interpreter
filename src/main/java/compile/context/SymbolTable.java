package compile.context;

import compile.exceptions.VariableExistsException;
import compile.exceptions.VariableUndeclaredException;
import types.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, Type> table = new HashMap<>();

    public void add(String name, Type type) {
        if (table.containsKey(name)) {
            throw new VariableExistsException("Variable " + name + " has already been declared.");
        }
        table.put(name, type);
    }

    public Type get(String name) {
        if (!table.containsKey(name)) {
            throw new VariableUndeclaredException("Variable " + name + " has not been declared.");
        }
        return table.get(name);
    }

    public boolean contains(String name) {
        return table.containsKey(name);
    }
}

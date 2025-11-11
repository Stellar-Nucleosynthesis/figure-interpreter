package compile.context;

import compile.exceptions.VariableExistsException;
import compile.exceptions.VariableUndeclaredException;
import lombok.Getter;
import types.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    @Getter
    private final SymbolTable outer;

    public SymbolTable(SymbolTable outer){
        this.outer = outer;
    }

    private final Map<String, Type> table = new HashMap<>();

    public void add(String name, Type type) {
        if (contains(name)) {
            throw new VariableExistsException("Variable " + name + " has already been declared.");
        }
        table.put(name, type);
    }

    public Type get(String name) {
        if (!contains(name)) {
            throw new VariableUndeclaredException("Variable " + name + " has not been declared.");
        }
        if(table.containsKey(name)){
            return table.get(name);
        } else {
            return outer.get(name);
        }
    }

    public boolean contains(String name) {
        return table.containsKey(name) || (outer != null && outer.contains(name));
    }
}

package runtime.environment;

import compile.exceptions.VariableUndeclaredException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class VariableValues {
    @Getter
    private final Map<String, Object> variables = new HashMap<>();

    public Object get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        } else {
            throw new VariableUndeclaredException("Variable not declared: " + name);
        }
    }

    public void set(String name, Object val) {
        variables.put(name, val);
    }
}

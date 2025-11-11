package runtime.function;

import runtime.environment.ExecutionContext;

import java.util.List;

public interface Function {
    Object execute(ExecutionContext context, List<Object> arguments);
}

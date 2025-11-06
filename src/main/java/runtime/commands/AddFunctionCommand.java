package runtime.commands;

import runtime.environment.ExecutionContext;
import runtime.function.Function;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddFunctionCommand implements Command {
    private final String name;
    private final Function function;

    @Override
    public void execute(ExecutionContext context) {
        context.setVar(name, function);
    }
}

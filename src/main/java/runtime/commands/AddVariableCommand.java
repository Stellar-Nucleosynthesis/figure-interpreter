package runtime.commands;

import runtime.environment.ExecutionContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddVariableCommand implements Command {
    private String name;
    private Object value;

    @Override
    public void execute(ExecutionContext context) {
        context.setVar(name, value);
    }
}

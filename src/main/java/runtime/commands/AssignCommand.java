package runtime.commands;

import runtime.environment.ExecutionContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignCommand implements Command {
    private String to;
    private String from;

    @Override
    public void execute(ExecutionContext context) {
        Object value = context.getVar(from);
        context.setVar(to, value);
    }
}

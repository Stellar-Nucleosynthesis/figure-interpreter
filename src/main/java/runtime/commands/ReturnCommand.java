package runtime.commands;

import runtime.environment.ExecutionContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReturnCommand implements Command {
    private String resultVar;
    private String funcResult;

    @Override
    public void execute(ExecutionContext context) {
        Object result = context.getVar(resultVar);
        context.setVar(funcResult, result);
    }
}

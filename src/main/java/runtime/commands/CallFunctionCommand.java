package runtime.commands;

import runtime.environment.ExecutionContext;
import lombok.AllArgsConstructor;
import runtime.function.Function;

import java.util.List;

@AllArgsConstructor
public class CallFunctionCommand implements Command {
    private final String name;
    private final List<String> argNames;
    private final String returnTo;

    @Override
    public void execute(ExecutionContext context) {
        Function func = (Function) context.getVar(name);
        List<Object> args = argNames.stream().map(context::getVar).toList();
        Object res = func.execute(context, args);
        context.setVar(returnTo, res);
    }
}

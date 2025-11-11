package runtime.commands;

import lombok.AllArgsConstructor;
import runtime.environment.ExecutionContext;
import types.Type;

@AllArgsConstructor
public class ConvertTypeCommand implements Command {
    private Type typeTo;
    private String nameTo;
    private Type typeFrom;
    private String nameFrom;

    @Override
    public void execute(ExecutionContext context) {
        Object valFrom = context.getVar(nameFrom);
        Object valTo = typeFrom.convert(valFrom, typeTo);
        context.setVar(nameTo, valTo);
    }
}

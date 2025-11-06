package runtime.commands;

import lombok.AllArgsConstructor;
import runtime.environment.ExecutionContext;
import types.Type;
import types.Variable;

import static types.TypeConversion.convert;

@AllArgsConstructor
public class ConvertTypeCommand implements Command {
    private Type type1;
    private String name1;
    private Type type2;
    private String name2;

    @Override
    public void execute(ExecutionContext context) {
        Object val1 = context.getVar(name1);
        Object val2 = convert(new Variable(type1, val1), type2);
        context.setVar(name2, val2);
    }
}

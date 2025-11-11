package builtIns.io;

import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class PrintIntFunction implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Integer val = (Integer) arguments.getFirst();
        System.out.println(val);
        return 0;
    }
}

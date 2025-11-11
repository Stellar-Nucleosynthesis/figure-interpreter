package builtIns.io;

import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class PrintDoubleFunction implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Double val = (Double) arguments.getFirst();
        System.out.println(val);
        return 0;
    }
}

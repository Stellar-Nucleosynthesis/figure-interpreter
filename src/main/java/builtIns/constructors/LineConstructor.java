package builtIns.constructors;

import figures.Line;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class LineConstructor implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Double k = (Double) arguments.get(0);
        Double b = (Double) arguments.get(1);
        return new Line(k, b);
    }
}

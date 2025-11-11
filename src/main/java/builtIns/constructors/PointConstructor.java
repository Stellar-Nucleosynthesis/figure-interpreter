package builtIns.constructors;

import figures.Point;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class PointConstructor implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Double x = (Double) arguments.get(0);
        Double y = (Double) arguments.get(1);
        return new Point(x, y);
    }
}

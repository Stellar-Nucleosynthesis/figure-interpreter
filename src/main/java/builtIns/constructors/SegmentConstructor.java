package builtIns.constructors;

import figures.Point;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class SegmentConstructor implements Function {

    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Point p1 = (Point) arguments.get(0);
        Point p2 = (Point) arguments.get(1);
        return new Segment(p1, p2);
    }
}

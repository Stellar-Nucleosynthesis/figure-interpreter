package builtIns.segmentUtils;

import figures.Point;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class SegmentMiddleFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Segment s = (Segment) arguments.getFirst();
        Point p1 = s.getP1();
        Point p2 = s.getP2();
        return new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }
}

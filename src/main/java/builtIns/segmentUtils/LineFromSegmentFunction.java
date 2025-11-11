package builtIns.segmentUtils;

import figures.Line;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class LineFromSegmentFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Segment s = (Segment) arguments.getFirst();
        double x1 = s.getP1().getX();
        double y1 = s.getP1().getY();
        double x2 = s.getP2().getX();
        double y2 = s.getP2().getY();

        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        return new Line(k, b);
    }
}

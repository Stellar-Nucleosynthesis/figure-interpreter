package builtIns.segmentUtils;

import figures.Line;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;
import utils.GeometryUtils;

import java.util.List;

public class LineFromSegmentFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Segment s = (Segment) arguments.getFirst();
        double x1 = s.getP1().getX();
        double y1 = s.getP1().getY();
        double x2 = s.getP2().getX();
        double y2 = s.getP2().getY();
        double dx = x2 - x1;

        double k;
        double b;
        if (Math.abs(dx) < GeometryUtils.EPS) {
            if (Math.abs(y2 - y1) < GeometryUtils.EPS) {
                return new Line(Double.NaN, Double.NaN);
            }
            k = Double.POSITIVE_INFINITY;
            b = x1;
            return new Line(k, b);
        }

        k = (y2 - y1) / dx;
        b = y1 - k * x1;
        return new Line(k, b);
    }
}

package builtIns.segmentUtils;

import figures.Line;
import figures.Point;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;
import utils.GeometryUtils;

import java.util.List;

public class AltitudeOnLineFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Line line = (Line) arguments.get(0);
        Point p = (Point) arguments.get(1);
        double x0 = p.getX();
        double y0 = p.getY();
        double k = line.getK();
        double b = line.getB();

        if (!Double.isInfinite(k) && Math.abs(y0 - (k * x0 + b)) < GeometryUtils.EPS) {
            return new Segment(p, new Point(x0 + 0.1, y0 + 0.1));
        }

        if (Double.isInfinite(k)) {
            Point intersect = new Point(b, y0);
            return new Segment(p, intersect);
        }

        if (Math.abs(k) < GeometryUtils.EPS) {
            Point intersect = new Point(x0, b);
            return new Segment(p, intersect);
        }

        double mPerp = -1.0 / k;
        double cPerp = y0 - mPerp * x0;

        double denom = k - mPerp;
        double xi = (cPerp - b) / denom;
        double yi = k * xi + b;

        Point intersect = new Point(xi, yi);
        return new Segment(p, intersect);
    }
}

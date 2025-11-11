package builtIns.segmentUtils;

import figures.Line;
import figures.Point;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class AltitudeOnLineFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Line line = (Line) arguments.get(0);
        Point p = (Point) arguments.get(1);
        double x0 = p.getX();
        double y0 = p.getY();
        double k = line.getK();

        double k2 = -1.0 / k;
        double b2 = y0 - k2 * x0;
        return new Line(k2, b2);
    }
}

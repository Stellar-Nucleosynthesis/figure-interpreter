package builtIns.quadUtils;

import figures.Point;
import figures.Quadrilateral;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class QuadAreaFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Quadrilateral quadrilateral = (Quadrilateral) arguments.getFirst();
        Point p1 = quadrilateral.getP1();
        Point p2 = quadrilateral.getP2();
        Point p3 = quadrilateral.getP3();
        Point p4 = quadrilateral.getP4();

        double x1 = p1.getX(), y1 = p1.getY();
        double x2 = p2.getX(), y2 = p2.getY();
        double x3 = p3.getX(), y3 = p3.getY();
        double x4 = p4.getX(), y4 = p4.getY();

        double sum = x1*y2 + x2*y3 + x3*y4 + x4*y1
                - (y1*x2 + y2*x3 + y3*x4 + y4*x1);

        return Math.abs(sum) / 2.0;
    }
}

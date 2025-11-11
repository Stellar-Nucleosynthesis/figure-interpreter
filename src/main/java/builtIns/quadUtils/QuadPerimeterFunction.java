package builtIns.quadUtils;

import figures.Point;
import figures.Quadrilateral;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

import static utils.GeometryUtils.len;
import static utils.GeometryUtils.vec;

public class QuadPerimeterFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Quadrilateral quadrilateral = (Quadrilateral) arguments.getFirst();
        Point p1 = quadrilateral.getP1();
        Point p2 = quadrilateral.getP2();
        Point p3 = quadrilateral.getP3();
        Point p4 = quadrilateral.getP4();
        return len(vec(p1, p2)) + len(vec(p2, p3)) +
                len(vec(p3, p4)) + len(vec(p4, p1));
    }
}

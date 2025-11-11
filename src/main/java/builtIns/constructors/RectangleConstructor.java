package builtIns.constructors;

import figures.Point;
import figures.Rectangle;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class RectangleConstructor implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Point p1 = (Point) arguments.get(0);
        Point p2 = (Point) arguments.get(1);
        Point p3 = (Point) arguments.get(2);
        Point p4 = (Point) arguments.get(3);
        return new Rectangle(p1, p2,  p3, p4);
    }
}

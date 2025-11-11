package figures;

import runtime.exceptions.InvalidFigureException;

import static utils.GeometryUtils.parallel;
import static utils.GeometryUtils.vec;

public class Parallelogram extends Quadrilateral {
    public Parallelogram(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
        if(!parallel(vec(p1, p2), vec(p3, p4)) || !parallel(vec(p1, p4), vec(p2, p3)))
            throw new InvalidFigureException("Sides of a parallelogram must be parallel");
    }
}

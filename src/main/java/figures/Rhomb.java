package figures;

import runtime.exceptions.InvalidFigureException;

import static utils.GeometryUtils.equal;
import static utils.GeometryUtils.sideLength;

public class Rhomb extends Parallelogram {
    public Rhomb(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
        double l1 = sideLength(p1, p2);
        double l2 = sideLength(p2, p3);
        double l3 = sideLength(p3, p4);
        double l4 = sideLength(p4, p1);
        if(!equal(l1, l2) || !equal(l2, l3) || !equal(l3, l4) || !equal(l4, l1))
            throw new InvalidFigureException("Sides of a rhomb must be equal");
    }
}

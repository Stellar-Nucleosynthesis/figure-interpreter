package figures;

import runtime.exceptions.InvalidFigureException;

import static utils.GeometryUtils.perpendicular;
import static utils.GeometryUtils.vec;

public class Rectangle extends Quadrilateral {
    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
        if(!perpendicular(vec(p1, p2), vec(p2, p3))
        || !perpendicular(vec(p2, p3), vec(p3, p4))
        || !perpendicular(vec(p3, p4), vec(p4, p1)))
            throw new InvalidFigureException("Angles of a rectangle must be right");
    }
}

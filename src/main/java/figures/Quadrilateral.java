package figures;

import runtime.exceptions.InvalidFigureException;
import lombok.Data;

import java.awt.*;

import static utils.GeometryUtils.equal;
import static utils.GeometryUtils.segmentsIntersect;

@Data
public class Quadrilateral implements Figure {
    protected Point p1;
    protected Point p2;
    protected Point p3;
    protected Point p4;
    protected String name;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        checkPoints(p1, p2, p3, p4);
    }

    @Override
    public void show(Graphics g, java.awt.Point origin, double unit) {
        if (p1 == null || p2 == null || p3 == null || p4 == null)
            return;
        Point[] pts = {p1, p2, p3, p4};
        int[] xs = new int[4], ys = new int[4];
        for (int i = 0; i < 4; i++) {
            xs[i] = Figure.toScreenX(pts[i].getX(), origin, unit);
            ys[i] = Figure.toScreenY(pts[i].getY(), origin, unit);
        }
        g.drawPolygon(xs, ys, 4);

        if (name != null) {
            int cx = (xs[0] + xs[1] + xs[2] + xs[3]) / 4;
            int cy = (ys[0] + ys[1] + ys[2] + ys[3]) / 4;
            Figure.drawName(g, name, cx, cy);
        }
    }

    private void checkPoints(Point p1, Point p2, Point p3, Point p4){
        Point[] pts = {p1, p2, p3, p4};
        for(int i = 0; i < pts.length; i++){
            for(int j = i + 1; j < pts.length; j++){
                if(equal(pts[i], pts[j]))
                    throw new InvalidFigureException("Points of a quadrilateral must not be equal");
            }
        }
        if(segmentsIntersect(p1, p2, p3, p4))
            throw new InvalidFigureException("Sides of a quadrilateral must not intersect");
    }
}
package figures;

import runtime.exceptions.InvalidFigureException;
import lombok.Data;

import java.awt.*;

import static utils.GeometryUtils.equal;

@Data
public class Segment implements Figure {
    private Point p1;
    private Point p2;
    private String name;

    public Segment(Point p1, Point p2) {
        if(equal(p1, p2))
            throw new InvalidFigureException("Points of a segment must not be equal");
    }

    @Override
    public void show(Graphics g, java.awt.Point origin, double unit) {
        if (p1 == null || p2 == null)
            return;
        int x1 = Figure.toScreenX(p1.getX(), origin, unit);
        int y1 = Figure.toScreenY(p1.getY(), origin, unit);
        int x2 = Figure.toScreenX(p2.getX(), origin, unit);
        int y2 = Figure.toScreenY(p2.getY(), origin, unit);
        g.drawLine(x1, y1, x2, y2);

        p1.show(g, origin, unit);
        p2.show(g, origin, unit);

        if (name != null) {
            int xm = (x1 + x2) / 2;
            int ym = (y1 + y2) / 2;
            Figure.drawName(g, name, xm, ym);
        }
    }
}
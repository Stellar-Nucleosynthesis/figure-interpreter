package figures;

import lombok.Data;

import java.awt.*;

@Data
public class Point implements Figure {
    private double x;
    private double y;
    private String name;

    @Override
    public void show(Graphics g, java.awt.Point origin, double unit) {
        int sx = Figure.toScreenX(x, origin, unit);
        int sy = Figure.toScreenY(y, origin, unit);
        int r = Math.max(2, (int) (unit * 0.05));
        g.fillOval(sx - r, sy - r, 2 * r, 2 * r);

        Figure.drawName(g, name, sx, sy);
    }
}
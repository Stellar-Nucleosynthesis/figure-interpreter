package figures;

import lombok.Data;

import java.awt.*;

@Data
public class Line implements Figure {
    private double k;
    private double b;
    private String name;

    @Override
    public void show(Graphics g, java.awt.Point origin, double unit) {
        java.awt.Rectangle clip = g.getClipBounds();

        double worldLeftX  = (clip.x - origin.x) / unit;
        double worldRightX = (clip.x + clip.width - origin.x) / unit;

        double worldYLeft  = k * worldLeftX + b;
        double worldYRight = k * worldRightX + b;

        int sx1 = Figure.toScreenX(worldLeftX, origin, unit);
        int sy1 = Figure.toScreenY(worldYLeft, origin, unit);
        int sx2 = Figure.toScreenX(worldRightX, origin, unit);
        int sy2 = Figure.toScreenY(worldYRight, origin, unit);

        g.drawLine(sx1, sy1, sx2, sy2);

        if (name != null) {
            int xm = sx1 + (sx2 - sx1) / 3;
            int ym = sy1 + (sy2 - sy1) / 3;
            Figure.drawName(g, name, xm, ym);
        }
    }
}
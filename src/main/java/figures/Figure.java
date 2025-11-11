package figures;

import java.awt.*;

public interface Figure {
    void show(Graphics g, java.awt.Point origin, double unit);
    String getName();
    void setName(String name);

    static int toScreenX(double worldX, java.awt.Point origin, double unit) {
        return (int) Math.round(origin.x + worldX * unit);
    }

    static int toScreenY(double worldY, java.awt.Point origin, double unit) {
        return (int) Math.round(origin.y - worldY * unit);
    }

    static void drawName(Graphics g, String name, int x, int y) {
        if (name != null && !name.isEmpty()) {
            g.drawString(name, x + 5, y - 5);
        }
    }
}

package utils;

import figures.Point;

public class GeometryUtils {
    public static final double EPS = 1e-2;

    public static Vector vec(Point a, Point b) {
        return new Vector(b.getX() - a.getX(), b.getY() - a.getY());
    }

    public static double dot(Vector u, Vector v) {
        return u.getX() * v.getX() + u.getY() * v.getY();
    }

    public static double cross(Vector u, Vector v) {
        return u.getX() * v.getY() - u.getY() * v.getX();
    }

    public static double len(Vector u) {
        return Math.hypot(u.getX(), u.getY());
    }

    public static boolean parallel(Vector u, Vector v) {
        return Math.abs(cross(u, v)) <= EPS * (len(u) * len(v) + EPS);
    }

    public static boolean perpendicular(Vector u, Vector v) {
        return Math.abs(dot(u, v)) <= EPS * (len(u) * len(v) + EPS);
    }

    public static boolean equal(double a, double b) {
        return Math.abs(a - b) <= EPS;
    }

    public static boolean equal(Point a, Point b) {
        return sideLength(a, b) <= EPS;
    }

    public static boolean collinear(Point a, Point b, Point c) {
        return Math.abs(cross(vec(a, b), vec(a, c))) <= EPS;
    }

    private static int sgn(double x) {
        if (Math.abs(x) <= EPS)
            return 0;
        return x > 0 ? 1 : -1;
    }

    public  static boolean onSegment(Point a, Point b, Point p) {
        double minX = Math.min(a.getX(), b.getX()) - EPS;
        double maxX = Math.max(a.getX(), b.getX()) + EPS;
        double minY = Math.min(a.getY(), b.getY()) - EPS;
        double maxY = Math.max(a.getY(), b.getY()) + EPS;
        return collinear(a, b, p) && p.getX() >= minX && p.getX() <= maxX
                && p.getY() >= minY && p.getY() <= maxY;
    }

    public static boolean segmentsIntersect(Point a, Point b, Point c, Point d) {
        Vector ab = vec(a, b);
        Vector ac = vec(a, c);
        Vector ad = vec(a, d);
        Vector cd = vec(c, d);
        Vector ca = vec(c, a);
        Vector cb = vec(c, b);

        double cross1 = cross(ab, ac);
        double cross2 = cross(ab, ad);
        double cross3 = cross(cd, ca);
        double cross4 = cross(cd, cb);

        int s1 = sgn(cross1);
        int s2 = sgn(cross2);
        int s3 = sgn(cross3);
        int s4 = sgn(cross4);

        if (s1 * s2 < 0 && s3 * s4 < 0)
            return true;

        if (s1 == 0 && onSegment(a, b, c))
            return true;
        if (s2 == 0 && onSegment(a, b, d))
            return true;
        if (s3 == 0 && onSegment(c, d, a))
            return true;
        return
                s4 == 0 && onSegment(c, d, b);
    }

    public static double sideLength(Point a, Point b) {
        return Math.hypot(b.getX() - a.getX(), b.getY() - a.getY());
    }
}
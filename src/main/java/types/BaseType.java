package types;

import figures.*;
import types.exceptions.TypeConversionException;

public enum BaseType implements Type {
    INT, DOUBLE, POINT, SEGMENT, LINE, QUAD, PARAL, TRAPEZ, RHOMB, RECT, SQUARE;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean canConvertTo(Type target) {
        if (!(target instanceof BaseType t))
            return false;
        if(this == t)
            return true;

        if (this == INT && t == DOUBLE)
            return true;
        if (this == DOUBLE && t == INT)
            return false;

        return switch (this) {
            case SQUARE -> t == RECT || t == PARAL || t == QUAD || t == RHOMB || t == TRAPEZ;
            case RECT, RHOMB -> t == PARAL || t == QUAD;
            case PARAL, TRAPEZ -> t == QUAD;
            default -> false;
        };
    }

    @Override
    public Object convert(Object value, Type target) {
        if(!canConvertTo(target))
            throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);

        switch (this) {
            case INT:
                if(target == DOUBLE)
                    return ((Integer) value).doubleValue();
            case SQUARE:
                Quadrilateral q1 = (Quadrilateral) value;
                return convertSquare(q1.getP1(), q1.getP2(), q1.getP3(), q1.getP4(), target);
            case RECT, RHOMB:
                Quadrilateral q2 = (Quadrilateral) value;
                return convertRectRhomb(q2.getP1(), q2.getP2(), q2.getP3(), q2.getP4(), target);
            case PARAL, TRAPEZ:
                Quadrilateral q3 = (Quadrilateral) value;
                return convertParalTrapez(q3.getP1(), q3.getP2(), q3.getP3(), q3.getP4(), target);
            default:
                throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);
        }
    }

    private Object convertSquare(Point p1, Point p2, Point p3, Point p4, Type target) {
        return switch (target) {
            case RECT -> new Rectangle(p1, p2, p3, p4);
            case PARAL -> new Parallelogram(p1, p2, p3, p4);
            case QUAD -> new Quadrilateral(p1, p2, p3, p4);
            case RHOMB -> new Rhomb(p1, p2, p3, p4);
            case TRAPEZ -> new Trapezoid(p1, p2, p3, p4);
            default -> throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);
        };
    }

    private Object convertRectRhomb(Point p1, Point p2, Point p3, Point p4, Type target) {
        return switch (target) {
            case PARAL -> new Parallelogram(p1, p2, p3, p4);
            case QUAD -> new Quadrilateral(p1, p2, p3, p4);
            default -> throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);
        };
    }

    private Object convertParalTrapez(Point p1, Point p2, Point p3, Point p4, Type target) {
        if(target == QUAD) {
            return new Quadrilateral(p1, p2, p3, p4);
        }
        throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);
    }
}

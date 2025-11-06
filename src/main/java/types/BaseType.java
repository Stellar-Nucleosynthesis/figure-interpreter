package types;

public enum BaseType implements Type {
    INT, DOUBLE, POINT, SEGMENT, LINE, QUAD, PARAL, TRAPEZ, RHOMB, RECT, SQUARE;

    @Override
    public String getName() {
        return name();
    }
}

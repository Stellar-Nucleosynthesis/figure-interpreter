package types;

public interface Type {
    String getName();
    boolean canConvertTo(Type target);
    Object convert(Object value, Type target);
}

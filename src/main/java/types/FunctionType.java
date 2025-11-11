package types;

import lombok.AllArgsConstructor;
import lombok.Data;
import types.exceptions.TypeConversionException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class FunctionType implements Type {
    private Type returns;
    private List<Type> accepts;

    @Override
    public String getName() {
        return returns.getName() + " (" +
                accepts.stream().map(Type::getName)
                        .collect(Collectors.joining(", ")) + ")";
    }

    @Override
    public boolean canConvertTo(Type target) {
        return false;
    }

    @Override
    public Object convert(Object value, Type target) {
        throw new TypeConversionException("Cannot convert from " + getName() + " to " + target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof FunctionType that))
            return false;
        return Objects.equals(returns, that.returns)
                && Objects.equals(accepts, that.accepts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returns, accepts);
    }

    @Override
    public String toString() {
        return getName();
    }
}

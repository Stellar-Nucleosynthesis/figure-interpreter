package types;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
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

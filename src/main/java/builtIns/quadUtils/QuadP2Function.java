package builtIns.quadUtils;

import figures.Quadrilateral;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class QuadP2Function implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Quadrilateral q = (Quadrilateral) arguments.getFirst();
        return q.getP2();
    }
}
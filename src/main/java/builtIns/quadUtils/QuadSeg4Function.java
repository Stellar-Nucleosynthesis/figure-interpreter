package builtIns.quadUtils;

import figures.Quadrilateral;
import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class QuadSeg4Function implements Function {
    @Override
    public Object execute(ExecutionContext outerContext, List<Object> arguments) {
        Quadrilateral q = (Quadrilateral) arguments.getFirst();
        return new Segment(q.getP4(), q.getP1());
    }
}
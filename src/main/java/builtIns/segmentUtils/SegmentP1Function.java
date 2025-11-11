package builtIns.segmentUtils;

import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class SegmentP1Function implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Segment segment = (Segment) arguments.getFirst();
        return segment.getP1();
    }
}

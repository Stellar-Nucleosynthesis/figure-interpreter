package builtIns.segmentUtils;

import figures.Segment;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

import static utils.GeometryUtils.len;
import static utils.GeometryUtils.vec;

public class SegmentLengthFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Segment segment = (Segment) arguments.getFirst();
        return len(vec(segment.getP1(), segment.getP2()));
    }
}

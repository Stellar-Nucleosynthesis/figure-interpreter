package builtIns.segmentUtils;

import figures.Line;
import runtime.environment.ExecutionContext;
import runtime.function.Function;

import java.util.List;

public class LineBFunction implements Function {
    @Override
    public Object execute(ExecutionContext context, List<Object> arguments) {
        Line line = (Line) arguments.getFirst();
        return line.getB();
    }
}

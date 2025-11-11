package builtIns.quadUtils;

import runtime.environment.ExecutionContext;

public class QuadUtilsExecutionContext extends ExecutionContext {
    public QuadUtilsExecutionContext(ExecutionContext outer) {
        super(outer);
        setVar("ПЕРША_ВЕРШИНА", new QuadP1Function());
        setVar("ДРУГА_ВЕРШИНА",  new QuadP2Function());
        setVar("ТРЕТЯ_ВЕРШИНА", new QuadP3Function());
        setVar("ЧЕТВЕРТА_ВЕРШИНА",  new QuadP4Function());

        setVar("ПЕРША_СТОРОНА", new QuadSeg1Function());
        setVar("ДРУГА_СТОРОНА", new QuadSeg2Function());
        setVar("ТРЕТЯ_СТОРОНА", new QuadSeg3Function());
        setVar("ЧЕТВЕРТА_СТОРОНА", new QuadSeg4Function());

        setVar("ПЕРИМЕТР", new QuadPerimeterFunction());
        setVar("ПЛОЩА", new QuadAreaFunction());
    }
}

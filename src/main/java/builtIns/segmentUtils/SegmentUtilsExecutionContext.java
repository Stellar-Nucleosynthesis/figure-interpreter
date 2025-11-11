package builtIns.segmentUtils;

import runtime.environment.ExecutionContext;

public class SegmentUtilsExecutionContext extends ExecutionContext {
    public SegmentUtilsExecutionContext(ExecutionContext outer) {
        super(outer);
        setVar("ПЕРША_ТОЧКА", new SegmentP1Function());
        setVar("ДРУГА_ТОЧКА", new SegmentP2Function());
        setVar("НАХИЛ", new LineKFunction());
        setVar("ЗМІЩЕННЯ", new LineBFunction());

        setVar("ВИСОТА_НА_ПРЯМУ", new AltitudeOnLineFunction());
        setVar("ПРЯМА_З_ВІДРІЗКА", new LineFromSegmentFunction());
        setVar("ДОВЖИНА_ВІДРІЗКА", new SegmentLengthFunction());
        setVar("СЕРЕДИНА_ВІДРІЗКА", new SegmentMiddleFunction());
    }
}

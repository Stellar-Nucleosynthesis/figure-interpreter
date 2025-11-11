package builtIns.constructors;

import runtime.environment.ExecutionContext;

public class ConstructorExecutionContext extends ExecutionContext {
    public ConstructorExecutionContext(ExecutionContext outer) {
        super(outer);
        setVar("ТОЧКА_ВІД", new PointConstructor());
        setVar("ВІДРІЗОК_ВІД", new SegmentConstructor());
        setVar("ПРЯМА_ВІД", new LineConstructor());
        setVar("ЧОТИРИКУТНИК_ВІД", new QuadrilateralConstructor());
        setVar("РОМБ_ВІД", new RhombConstructor());
        setVar("ТРАПЕЦІЯ_ВІД", new TrapezoidConstructor());
        setVar("ПАРАЛЕЛОГРАМ_ВІД", new ParallelogramConstructor());
        setVar("ПРЯМОКУТНИК_ВІД", new RectangleConstructor());
        setVar("КВАДРАТ_ВІД", new SquareConstructor());
    }
}

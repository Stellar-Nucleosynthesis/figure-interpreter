package builtIns.constructors;

import runtime.environment.ExecutionContext;

public class ConstructorExecutionContext extends ExecutionContext {
    public ConstructorExecutionContext(ExecutionContext outer) {
        super(outer);
        setVar("ТОЧКА_ІЗ", new PointConstructor());
        setVar("ВІДРІЗОК_ІЗ", new SegmentConstructor());
        setVar("ЛІНІЯ_ІЗ", new LineConstructor());
        setVar("ЧОТИРИКУТНИК_ІЗ", new QuadrilateralConstructor());
        setVar("РОМБ_ІЗ", new RhombConstructor());
        setVar("ТРАПЕЦІЯ_ІЗ", new TrapezoidConstructor());
        setVar("ПАРАЛЕЛОГРАМ_ІЗ", new ParallelogramConstructor());
        setVar("ПРЯМОКУТНИК_ІЗ", new RectangleConstructor());
        setVar("КВАДРАТ_ІЗ", new SquareConstructor());
    }
}

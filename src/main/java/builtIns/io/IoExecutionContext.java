package builtIns.io;

import runtime.environment.ExecutionContext;

public class IoExecutionContext extends ExecutionContext {
    public IoExecutionContext(ExecutionContext outer) {
        super(outer);
        setVar("ВИВЕСТИ_ЦІЛЕ", new PrintIntFunction());
        setVar("ВИВЕСТИ_ДІЙСНЕ", new PrintDoubleFunction());
    }
}

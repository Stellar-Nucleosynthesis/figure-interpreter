package runtime.commands;

import runtime.environment.ExecutionContext;

public interface Command {
    void execute(ExecutionContext context);
}

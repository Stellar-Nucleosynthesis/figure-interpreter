package runtime.commands;

import runtime.environment.ExecutionContext;
import figures.Figure;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowCommand implements Command {
    private String name;

    @Override
    public void execute(ExecutionContext context) {
        Object figure = context.getVar(name);
        context.showFigure((Figure) figure);
    }
}

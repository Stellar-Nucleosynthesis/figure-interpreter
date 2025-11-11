package runtime.commands;

import figures.Figure;
import lombok.AllArgsConstructor;
import runtime.environment.ExecutionContext;

@AllArgsConstructor
public class AddFigureNameCommand implements Command{
    private final String id;
    private final String name;

    @Override
    public void execute(ExecutionContext context) {
        Figure fig = (Figure) context.getVar(id);
        fig.setName(name);
    }
}

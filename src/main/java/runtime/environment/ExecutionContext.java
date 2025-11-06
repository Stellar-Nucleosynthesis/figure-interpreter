package runtime.environment;

import figures.Figure;
import lombok.Getter;

public class ExecutionContext {
    @Getter
    private final VariableValues variableValues = new VariableValues();
    @Getter
    private final ShowList showList = new ShowList();

    public void setVar(String name, Object val){
        variableValues.set(name, val);
    }

    public Object getVar(String name){
        return variableValues.get(name);
    }

    public void showFigure(Figure figure){
        showList.add(figure);
    }
}

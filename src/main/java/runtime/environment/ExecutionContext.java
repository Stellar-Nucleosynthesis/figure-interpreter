package runtime.environment;

import figures.Figure;
import lombok.Getter;

public class ExecutionContext {
    @Getter
    private final ExecutionContext outer;

    @Getter
    private final VariableValues variableValues = new VariableValues();
    @Getter
    private final ShowList showList = new ShowList();

    public ExecutionContext(ExecutionContext outer) {
        this.outer = outer;
    }

    public void setVar(String name, Object val){
        if (outer != null && outer.hasVar(name)){
            outer.setVar(name, val);
        } else {
            variableValues.set(name, val);
        }
    }

    public Object getVar(String name){
        if (outer != null && outer.hasVar(name)){
            return outer.getVar(name);
        } else {
            return variableValues.get(name);
        }
    }

    public boolean hasVar(String name){
        return variableValues.getVariables().containsKey(name)
                || (outer != null && outer.hasVar(name));
    }

    public void showFigure(Figure figure){
        showList.add(figure);
    }

    @Override
    public String toString() {
        return variableValues.getVariables() + "\n" + showList.getFigures();
    }
}

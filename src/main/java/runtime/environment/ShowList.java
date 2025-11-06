package runtime.environment;

import figures.Figure;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ShowList {
    @Getter
    private final List<Figure> figures = new ArrayList<>();

    public void add(Figure figure) {
        figures.add(figure);
    }
}

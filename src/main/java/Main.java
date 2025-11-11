import builtIns.constructors.ConstructorExecutionContext;
import builtIns.constructors.ConstructorSymbolTable;
import compile.translation.NodeAttributes;
import figures.Figure;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import runtime.commands.Command;
import runtime.environment.ExecutionContext;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JPanel {
    private final List<Figure> toShow = new ArrayList<>();

    public Main() throws IOException {
        CharStream input = CharStreams.fromFileName("src/main/resources/example.my");
        GeometryLexer lexer = new GeometryLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GeometryParser parser = new GeometryParser(tokens);
        parser.removeErrorListeners();

        parser.addErrorListener(new FormattedErrorListener());
        ParseTree tree = parser.program();

        GeometryVisitorImpl visitor = new GeometryVisitorImpl(new ConstructorSymbolTable(null));
        NodeAttributes res = visitor.visit(tree);
        System.out.println(res);

        List<Command> code = res.getCode();
        ExecutionContext context = new ExecutionContext(new ConstructorExecutionContext(null));
        for (Command command : code) {
            command.execute(context);
        }
        System.out.println(context);

        System.out.println(tree.toStringTree(parser));

        this.toShow.addAll(context.getShowList().getFigures());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point origin = new Point(getWidth() / 2, getHeight() / 2);
        double unit = 20;

        Color init = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, origin.y, getWidth(), origin.y);
        g.drawLine(origin.x, 0, origin.x, getHeight());
        g.setColor(init);

        for (Figure figure : toShow) {
            figure.show(g, origin, unit);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Line Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            try {
                frame.add(new Main());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}

import builtIns.constructors.ConstructorExecutionContext;
import builtIns.constructors.ConstructorSymbolTable;
import builtIns.io.IoExecutionContext;
import builtIns.io.IoSymbolTable;
import builtIns.quadUtils.QuadUtilsExecutionContext;
import builtIns.quadUtils.QuadUtilsSymbolTable;
import builtIns.segmentUtils.SegmentUtilsExecutionContext;
import builtIns.segmentUtils.SegmentUtilsSymbolTable;
import compile.translation.NodeAttributes;
import figures.Figure;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import runtime.commands.Command;
import runtime.environment.ExecutionContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JPanel {
    private static final List<Figure> toShow = new ArrayList<>();

    private double unit = 20;
    private double panX = 0, panY = 0;
    private Point lastDrag = null;

    public Main() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { lastDrag = e.getPoint(); }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                panX += p.x - lastDrag.x;
                panY += p.y - lastDrag.y;
                lastDrag = p;
                repaint();
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);

        addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            double scale = Math.pow(1.1, -notches);
            Point mouse = e.getPoint();
            Point2D.Double center = new Point2D.Double(getWidth() / 2.0, getHeight() / 2.0);

            double worldX = (mouse.x - center.x - panX) / unit;
            double worldY = (mouse.y - center.y - panY) / unit;

            unit *= scale;

            panX = mouse.x - center.x - worldX * unit;
            panY = mouse.y - center.y - worldY * unit;
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point origin = new Point((int) (getWidth() / 2.0 + panX), (int) (getHeight() / 2.0 + panY));
        double unitLocal = unit;

        Color init = g.getColor();
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, origin.y, getWidth(), origin.y);
        g.drawLine(origin.x, 0, origin.x, getHeight());
        g.setColor(init);

        for (Figure figure : toShow) {
            figure.show(g, origin, unitLocal);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar figure-interpreter.jar <file>");
            return;
        }
        CharStream input = CharStreams.fromFileName(args[0]);
        GeometryLexer lexer = new GeometryLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GeometryParser parser = new GeometryParser(tokens);
        parser.removeErrorListeners();

        FormattedErrorListener errorListener = new FormattedErrorListener();
        parser.addErrorListener(errorListener);
        ParseTree tree = parser.program();
        if (errorListener.hasErrors()) {
            for(String error : errorListener.getErrors())
                System.err.println(error);
            return;
        }

        GeometryVisitorImpl visitor = new GeometryVisitorImpl(
                new ConstructorSymbolTable(
                        new IoSymbolTable(
                                new QuadUtilsSymbolTable(
                                        new SegmentUtilsSymbolTable(null)))));
        NodeAttributes res = visitor.visit(tree);
        if(!res.getErrors().isEmpty()) {
            for(String error : res.getErrors())
                System.err.println(error);
            return;
        }

        List<Command> code = res.getCode();
        ExecutionContext context = new ExecutionContext(
                new ConstructorExecutionContext(
                        new IoExecutionContext(
                                new QuadUtilsExecutionContext(
                                        new SegmentUtilsExecutionContext(null)))));
        try{
            for (Command command : code) {
                command.execute(context);
            }
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            return;
        }
        toShow.addAll(context.getShowList().getFigures());

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Geometry interpreter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new Main());
            frame.setVisible(true);
        });
    }
}

package checkersresit.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.Stack;

public class BoardSquare extends Pane {
    private Stack<Circle> checkerStack = new Stack<>();

    public BoardSquare(double width, double height, Color color) {
        Rectangle square = new Rectangle();
        square.setFill(color);
        square.setStroke(Color.BLACK);
        square.setWidth(width);
        square.setHeight(height);
        this.getChildren().add(square);
    }
}

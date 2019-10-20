package checkersresit.views;

import checkersresit.controllers.HistoryController;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.util.Stack;

public class SquareController {
    private BoardSquare square;
    private int player;
    private Stack<Circle> checkerStack = new Stack<>();
    private HistoryController historyController;

    public SquareController(BoardSquare square) {
        historyController = new HistoryController();
        this.square = square;
        this.player = 0;
    }

    public BoardSquare getSquare() { return square; }

    public void addChecker(Color color, double r, int player) {
            this.player = player;
            Circle c;
            c =  createChecker(color, r);
            square.getChildren().add(c);
    }

    private Circle createChecker(Color color, double radius) {
        Circle checker = new Circle();

        if(checkerStack.empty()) {
            checker.setCenterY(radius - radius/2.5);
        } else {
           historyController.addToHistory("Invalid move!");
        }

        checker.setCenterX(radius);
        checker.setRadius(radius/2.5);
        String shadowColor = "#fff";
        checker.setStyle("-fx-effect: dropshadow(gaussian, "+shadowColor+", 10, 0, 0, 0);");
        checker.setFill(color);
        checkerStack.push(checker);

        return checker;
    }

    public void deleteChecker() {
        if (!this.checkerStack.isEmpty()) {
            square.getChildren().remove(checkerStack.lastElement());
            checkerStack.pop();
        }
    }

    public Paint getColor() {
        if(this.checkerStack.isEmpty()) {
            return Color.CRIMSON;
        }
        return checkerStack.peek().getFill();
    }

    public int checkEmptySquare() {
        return checkerStack.size();
    }

}

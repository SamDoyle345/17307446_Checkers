package checkersresit.views;

import checkersresit.controllers.GameController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BoardAndCommandBox extends HBox {

    private SquareController[][] squareControllers = new SquareController[8][8];

    public BoardAndCommandBox(Stage primaryStage, String player1Name, String player2Name) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();
        double checkerRadius = screenWidth/20;
        GameController gameController = new GameController(screenWidth/20, player1Name, player2Name, this);

        HBox root = new HBox();
        VBox board = new VBox();
        root.getChildren().addAll(board, new HistoryView(screenWidth * 0.19, screenHeight * 0.9, gameController));
        root.setSpacing(-350);

        StackPane colLabelsTop = new StackPane();
        Rectangle labelBackgroundTop = new Rectangle();
        labelBackgroundTop.setHeight(screenHeight * 0.02);
        labelBackgroundTop.setWidth(screenWidth * 0.808);
        labelBackgroundTop.setStroke(Color.WHITE);
        labelBackgroundTop.setFill(Color.WHITE);
        Text labelsTop = new Text();
        labelsTop.setFont(new Font(screenHeight * 0.015));
        labelsTop.setText("               A                                             B                                             C                                        D                                        E                                       F                                       G                                        H");
        labelsTop.setFill(Color.BLACK);
        colLabelsTop.getChildren().addAll(labelBackgroundTop, labelsTop);
        colLabelsTop.setAlignment(Pos.TOP_LEFT);
        board.getChildren().addAll(colLabelsTop);

        TextField commandLine = new TextField();
        commandLine.setPrefSize(screenWidth * 0.01, screenHeight * 0.01);
        commandLine.setFocusTraversable(false); // unfocus the command line
        Button submitCommand = new Button("Submit");
        submitCommand.setPrefSize(screenWidth * 0.05, screenHeight * 0.01);
        submitCommand.setAlignment(Pos.CENTER);
        gameController.CommandBoxController().setup(submitCommand, commandLine);


        for(int x = 0; x < 8; x++) {
            HBox row = new HBox();
            String rowNum = String.valueOf(x + 1);
            Text labelsLeft = new Text("\n" + rowNum);
            labelsLeft.setFont(new Font(screenHeight * 0.020));
            labelsLeft.setY(screenHeight * 0.12);
            row.getChildren().add(labelsLeft);
            board.getChildren().add(row);

            for(int y = 0; y < 8; y++) {
                if ((x + y) % 2 == 0) {
                    squareControllers[y][x] = new SquareController(new BoardSquare(screenWidth/10, screenHeight/10, Color.BLACK));
                } else {
                    squareControllers[y][x] = new SquareController(new BoardSquare(screenWidth/10, screenHeight/10, Color.WHITE));
                }
                row.getChildren().addAll(squareControllers[y][x].getSquare());
            }
            Text labelsRight = new Text("\n" + (x+1));
            labelsRight.setFont(new Font(screenHeight * 0.020));
            row.getChildren().add(labelsRight);
        }

        StackPane colLabelsBottom = new StackPane();
        Text labelsBottom = new Text();
        labelsBottom.setFont(new Font(screenHeight * 0.015));
        labelsBottom.setText("               A                                             B                                             C                                        D                                        E                                       F                                       G                                        H");
        labelsBottom.setFill(Color.BLACK);
        Rectangle labelBackgroundBottom = new Rectangle();
        labelBackgroundBottom.setHeight(screenHeight * 0.02);
        labelBackgroundBottom.setWidth(screenWidth * 0.808);
        labelBackgroundBottom.setStroke(Color.WHITE);
        labelBackgroundBottom.setFill(Color.WHITE);
        colLabelsBottom.getChildren().addAll(labelBackgroundBottom, labelsBottom);
        colLabelsBottom.setAlignment(Pos.BOTTOM_LEFT);
        board.getChildren().add(colLabelsBottom);



        Text names = new Text();
        names.setFont(new Font(20));
        names.setText("WHITE: " + player1Name + "                    VS                    " + "BLACK: " + player2Name);
        names.setFill(Color.WHITE);
        StackPane stack = new StackPane();
        Rectangle spacer = new Rectangle();
        spacer.setHeight(screenHeight * 0.05);
        spacer.setWidth(screenWidth);
        spacer.setStroke(Color.BLACK);
        stack.getChildren().addAll(spacer, names);
        board.getChildren().addAll(stack, commandLine, submitCommand);



        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public SquareController getSquare(int x, int y) {
        return squareControllers[x][y];
    }

}

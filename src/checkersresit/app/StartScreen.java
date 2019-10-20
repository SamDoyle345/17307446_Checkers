package checkersresit.app;

import checkersresit.controllers.GameController;
import checkersresit.views.BoardAndCommandBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartScreen extends VBox {
    public StartScreen(double screenWidth, double screenHeight, Stage primaryStage) {
        super(0);
        Label header = new Label("New Game");
        Label nameInfo = new Label();
        VBox title = new VBox(header);
        title.setAlignment(Pos.CENTER);
        header.setFont(new Font(screenWidth / 20));

        Label p1N = new Label("Player 1 Name: ");
        Label p2N = new Label("Player 2 Name: ");
        Line divider = new Line(screenWidth / 4 + 150, screenHeight / 4, screenWidth / 4 + screenWidth / 2 - 150, screenHeight / 4);
        TextField p1Name = new TextField("White");
        TextField p2Name = new TextField("Black");


        HBox inputsP1 = new HBox(p1N, p1Name);
        inputsP1.setSpacing(5);
        inputsP1.setAlignment(Pos.CENTER);

        HBox inputsP2 = new HBox(p2N, p2Name);
        inputsP2.setSpacing(5);
        inputsP2.setAlignment(Pos.CENTER);

        VBox inputs = new VBox(inputsP1, divider, inputsP2);
        inputs.setSpacing(5);
        inputs.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start");
        VBox start = new VBox(startButton);
        start.setAlignment(Pos.CENTER);

        this.getChildren().addAll(title, inputs, start);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        primaryStage.setX(screenWidth / 4);
        primaryStage.setY(screenHeight / 4);
        primaryStage.setWidth(screenWidth / 2);
        primaryStage.setHeight(screenHeight / 2);
        primaryStage.setResizable(false);

        startButton.setOnMouseClicked(e -> {
            String player1 = p1Name.getText();
            String player2 = p2Name.getText();
            if(player1.equals("")) {
                player1 = "Player 1";
            }
            if(player2.equals("")) {
                player2 = "Player 2";
            }
            nameInfo.setText("Player 1 : " + p1Name.getText() + " Player 2 : " + p2Name.getText());
            BoardAndCommandBox boardAndCommandBox = new BoardAndCommandBox(primaryStage, player1, player2);
        });
    }
}



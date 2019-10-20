package checkersresit.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CommandBoxController {

    GameController gameController;

    public CommandBoxController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setup (Button submitButton, TextField commandLine){
        submitButton.setOnMouseClicked(event -> {
            parseCommand(commandLine);
            event.consume();
        });
        commandLine.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)){
                parseCommand(commandLine);
            }
            event.consume();
        });
    }
    private void parseCommand(TextField commandLine) {
        String command = commandLine.getText();
        commandLine.setText("");
        gameController.checkCommand(command);
    }
}

package checkersresit.app;

import checkersresit.controllers.GameController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    Stage stage;
    double screenWidth;
    double screenHeight;
    GameController gameController;

    public SceneController(double screenWidth, double screenHeight, Stage stage) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        showStartScreen();
    }

    public void showStartScreen() {
        stage.setTitle("Checkers");
        stage.setScene(new Scene(new checkersresit.app.StartScreen(screenWidth, screenHeight, stage)));
        stage.setResizable(false);
        stage.show();
    }
}

package checkersresit.app;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(null); // defaults to Modena
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        new SceneController(screenWidth, screenHeight, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

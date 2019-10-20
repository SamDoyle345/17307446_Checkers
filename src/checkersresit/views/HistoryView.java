package checkersresit.views;

import checkersresit.controllers.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class HistoryView extends VBox {

    public HistoryView(double width, double height, GameController gameController){
        super(0);

        ListView<String> informationListView = new ListView<>();
        informationListView.setItems(gameController.getHistoryController().getHistory());
        Label informationLabel = new Label("Game History");
        informationLabel.setStyle("-fx-font-size: 18px");
        informationLabel.setAlignment(Pos.CENTER);
        informationListView.setPrefSize(width, height);

        this.getChildren().addAll(informationLabel, informationListView);
        this.setPrefSize(width, height);
    }

}

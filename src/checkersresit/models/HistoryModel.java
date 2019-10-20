package checkersresit.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoryModel implements History {

    private ObservableList<String> history;

    public HistoryModel() {
        history = FXCollections.observableArrayList();
    }

    @Override
    public void addHistory(String command) {
        history.add(command);
    }

    @Override
    public ObservableList<String> getHistory() {
        return history;
    }

}
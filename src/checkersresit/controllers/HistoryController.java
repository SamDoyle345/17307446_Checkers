package checkersresit.controllers;

import javafx.collections.ObservableList;
import checkersresit.models.HistoryModel;

public class HistoryController {

    private HistoryModel historyModel;

    public HistoryController() {
        historyModel = new HistoryModel();
    }

    public ObservableList<String> getHistory() {
        return historyModel.getHistory();
    }

    public int addToHistory(String text){
        historyModel.addHistory(text);
        return 1;
    }
}

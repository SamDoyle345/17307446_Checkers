package checkersresit.models;

import javafx.collections.ObservableList;

public interface History {
    void addHistory(String command);
    ObservableList<String> getHistory();
}

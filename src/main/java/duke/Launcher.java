package duke;

import duke.uiux.MainWindow;
import duke.uiux.Ui;
import duke.uiux.views.DialogueBox;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        System.out.println(Ui.class.getResource("/uiux/views/DialogueBox.fxml"));
        Application.launch(Ui.class, args);
    }
}

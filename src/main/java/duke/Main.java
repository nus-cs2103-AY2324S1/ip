package duke;

import javafx.application.Application;
import javafx.stage.Stage;

import duke.ui.MainWindow;

public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDuke(duke);

        stage.setScene(mainWindow.getScene());
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        stage.show();
    }
}

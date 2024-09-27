package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Controller for WindowTitle.
 */
public class WindowTitle extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChadBob");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
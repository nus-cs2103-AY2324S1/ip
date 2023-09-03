package cyrus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point for Cyrus Gui.
 */
public class Cyrus extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello world!");
        Scene scene = new Scene(helloWorld);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package koko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents a wrapper class for the Duke chatbot application.
 */
public class DukeGuiWrapper extends Application {

    private final Duke duke = new Duke("data/duke.txt");

    /**
     * Starts the Duke chatbot application.
     *
     * @param stage The stage to be used for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGuiWrapper.class.getResource("/view/DukeWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<DukeWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

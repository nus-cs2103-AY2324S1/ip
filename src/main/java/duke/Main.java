package duke;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.ChatWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Bryan - A Simple Todo Manager");
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/ChatWindow.fxml")
            );
            BorderPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(
                    Objects.requireNonNull(getClass()
                            .getResource("/css/application.css"))
                            .toExternalForm()
            );
            stage.setScene(scene);
            stage.setMinHeight(620);
            stage.setMinWidth(420);
            fxmlLoader.<ChatWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package sae;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class represents the entry point for the Sae GUI application using FXML.
 * It launches the GUI and initializes the main Sae instance.
 * The structure of this class has been adapted (with minor changes)
 * from a https://se-education.org/guides/tutorials/javaFx.html tutorial.
 */
public class Main extends Application {

    private final Sae sae = new Sae();

    /**
     * Starts the GUI application.
     *
     * @param stage The primary stage for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sae");
            fxmlLoader.<MainWindow>getController().setSae(sae);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

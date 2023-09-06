package kniaz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.gui.MainWindow;


//@@author Jeffry Lium
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
/**
 * A GUI for Kniaz using FXML.
 */
public class Main extends Application {

    private Kniaz kniaz = new Kniaz();

    /**
     * The method to start this application.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKniaz(kniaz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//@@author

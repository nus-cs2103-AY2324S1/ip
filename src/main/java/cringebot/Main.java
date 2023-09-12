package cringebot;

import java.io.IOException;

import cringebot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for CringeBot using FXML.
 */
public class Main extends Application {

    private final CringeBot cringeBot = new CringeBot("./data/data.ser");

    /**
     * Starts the GUI for CringeBot.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCringeBot(cringeBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

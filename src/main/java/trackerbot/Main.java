package trackerbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import trackerbot.exception.TrackerBotException;
import trackerbot.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TrackerBot trackerBot = TrackerBot.instantiate();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTrackerBot(trackerBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            trackerBot.handleSave();
        } catch (TrackerBotException e) {
            System.err.println(e.getMessage());
        }
    }
}

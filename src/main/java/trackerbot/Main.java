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
 * A GUI for TrackerBot using FXML.
 *
 * @version Level-10
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
            stage.setResizable(false);
            stage.titleProperty().set("TrackerBot");
            fxmlLoader.<MainWindow>getController().setTrackerBot(trackerBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to save the data from TrackerBot in the exit sequence.
     */
    @Override
    public void stop() {
        try {
            trackerBot.handleSave();
        } catch (TrackerBotException e) {
            System.err.println(e.getMessage());
        }
    }
}

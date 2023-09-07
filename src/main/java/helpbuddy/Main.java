package helpbuddy;

import java.io.IOException;

import helpbuddy.exception.HelpBuddyException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for HelpBuddy using FXML.
 */
public class Main extends Application {

    private HelpBuddy helpBuddy;
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
            helpBuddy = new HelpBuddy("data/tasks.txt");
            fxmlLoader.<MainWindow>getController().setHelpBuddy(helpBuddy);
        } catch (IOException e) {
            fxmlLoader.<MainWindow>getController().printErrorMessage(e.getMessage());
        } catch (HelpBuddyException e) {
            fxmlLoader.<MainWindow>getController().printErrorMessage(e.getMessage());
        }
    }
}

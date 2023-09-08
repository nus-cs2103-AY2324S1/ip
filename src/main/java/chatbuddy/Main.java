package chatbuddy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Chat Buddy using FXML.
 */
public class Main extends Application {

    private ChatBuddy chatBuddy = new ChatBuddy("data/tasks.txt");
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatBuddy(chatBuddy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        String exitMessage = chatBuddy.saveAndGetExitMessage();
        fxmlLoader.<MainWindow>getController().showMessage(exitMessage);
    }
}

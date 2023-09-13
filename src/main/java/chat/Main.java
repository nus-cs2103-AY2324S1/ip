package chat;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * @author juzzztinsoong
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            URL url = Main.class.getResource("/view/MainWindow.fxml");
            System.out.println( getClass().getResource(getClass().getSimpleName() + ".class") );
            assert url != null;
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            MainWindow mw = fxmlLoader.<MainWindow>getController();
            Chat chat = new Chat("data/chat.txt");
            mw.setChat(chat);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

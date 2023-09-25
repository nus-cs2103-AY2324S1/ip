package chat;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A launcher class for Chat using FXML.
 * @author juzzztinsoong
 */
public class Launcher {

    public static class Main extends Application {

        @Override
        public void start(Stage stage) {
            try {
                URL url = Main.class.getResource("/view/MainWindow.fxml");
                assert url != null : "MainWindow fxml not found";
            
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                AnchorPane ap = fxmlLoader.load();
                Scene scene = new Scene(ap);
                MainWindow mw = fxmlLoader.<MainWindow>getController();
                Chat chat = new Chat("data/chat.txt");

                mw.setChat(chat);
                stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/defaultpepe.jpg")));
                stage.setTitle("Tw*tch Chat");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Application.launch(Main.class, args);
    }
}

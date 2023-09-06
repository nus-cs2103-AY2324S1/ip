package chatbot;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import chatbot.gui.Gui;

public class Main extends Application {
    private ChatBot chatBot = new ChatBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<Gui>getController().setChatBot(chatBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

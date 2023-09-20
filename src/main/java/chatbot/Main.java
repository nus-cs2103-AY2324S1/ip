package chatbot;

import java.io.IOException;

import chatbot.gui.Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private ChatBot chatBot = new ChatBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("ChatBot");
            stage.setScene(scene);
            fxmlLoader.<Gui>getController().setChatBot(chatBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

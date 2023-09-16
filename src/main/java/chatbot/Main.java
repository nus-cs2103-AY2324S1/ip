package chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Main class for Chatbot.
 */
public class Main extends Application {

    private Chatbot chatbot = new Chatbot();

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML layout and its controller
        URL url = getClass().getResource("/view/Chatbot.fxml");
        System.out.println("URL: " + url);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Chatbot.fxml"));
        Parent root = loader.load();

        ChatbotController controller = loader.getController();
        controller.setChatbot(chatbot);

        stage.setTitle("Chatbot");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Entry for the main class
     * @param args String
     */
    public static void main(String[] args) {
        launch(args);
    }
}


package duke.uiux;

import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Encapsulates the UI of the chat bot.
 */
public class Ui extends Application {
    private Stage window; 
    private Duke duke;
    
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.duke = new Duke();
        this.window = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/MainWindow.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene mainScene = new Scene(anchorPane);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        try {
            window.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.setTitle("SpooderBot");
        window.show();
    }   

}

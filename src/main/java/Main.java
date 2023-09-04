import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for starting the Thorndike chatbot application.
 *
 * @author Ho Khee Wei
 */
public class Main extends Application {

    private Thorndike thorndike = new Thorndike();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(thorndike);
            stage.show();
            fxmlLoader.<MainWindow>getController().greet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thorndike.Thorndike;

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
            ap.getStyleClass().add("root");
            ModeControl.setWindow(ap);

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());

            Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Thorndike");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            fxmlLoader.<MainWindow>getController().setDuke(thorndike);
            fxmlLoader.<MainWindow>getController().greet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

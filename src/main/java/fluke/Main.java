package fluke;

import java.io.IOException;

import fluke.exceptions.FlukeException;
import fluke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Fluke fluke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fluke = new Fluke();
            fxmlLoader.<MainWindow>getController().setFlukeAndGreetUser(fluke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FlukeException f) {
            System.out.println(f.getMessage());
        }
    }
}

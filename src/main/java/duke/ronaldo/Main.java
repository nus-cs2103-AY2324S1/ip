package duke.ronaldo;

import java.io.IOException;

import duke.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private RonaldoSaysDo ronaldoSaysDo = new RonaldoSaysDo();
    private MainWindow mainWindow = new MainWindow();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ronaldoSaysDo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


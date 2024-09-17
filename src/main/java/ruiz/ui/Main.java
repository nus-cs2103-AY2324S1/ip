package ruiz.ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ruiz.Ruiz;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Ruiz ruiz = new Ruiz();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ruiz");
        stage.show();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRuiz(ruiz);
            stage.show();
            stage.setResizable(true);
            fxmlLoader.<MainWindow>getController().greet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

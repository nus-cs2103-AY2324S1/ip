package zean;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zean.gui.MainWindow;

/**
 * A GUI for Zean using FXML.
 */
public class Main extends Application {

    private Zean zean = new Zean("./data/zean.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Zean");
            fxmlLoader.<MainWindow>getController().setZean(zean);
            if (!this.zean.getInitErrorMsg().isEmpty()) {
                fxmlLoader.<MainWindow>getController().showMessage(this.zean.getInitErrorMsg());
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

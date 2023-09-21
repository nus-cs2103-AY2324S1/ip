package bellcurvegod.gui;

import java.io.IOException;

import bellcurvegod.BellCurveGod;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for BellCurveGod using FXML.
 */
public class Main extends Application {

    private final BellCurveGod bellCurveGod = new BellCurveGod();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bell Curve God");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBellCurveGod(bellCurveGod);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


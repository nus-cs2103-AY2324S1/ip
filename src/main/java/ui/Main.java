package main.java.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Botty;


/**
 * A GUI for Botty using FXML.
 */
public class Main extends Application {

    private Botty botty = new Botty();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert fxmlLoader != null : "Empty FxmlLoader";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Botty");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBotty(botty);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
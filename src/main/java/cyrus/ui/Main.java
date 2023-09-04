package cyrus.ui;

import java.io.IOException;

import cyrus.Cyrus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for initializing and loading the MainWindow.
 */
public class Main extends Application {
    private static final Cyrus CYRUS = new Cyrus();

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setCyrus(CYRUS);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

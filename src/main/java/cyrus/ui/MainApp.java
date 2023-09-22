package cyrus.ui;

import java.io.IOException;

import cyrus.Cyrus;
import cyrus.ui.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for initializing and loading the MainWindow.
 */
public class MainApp extends Application {
    private static final Cyrus CYRUS = new Cyrus();

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(this.getClass().getResource("/style/styles.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Cyrus");
            fxmlLoader.<MainWindow>getController().setCyrus(CYRUS);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

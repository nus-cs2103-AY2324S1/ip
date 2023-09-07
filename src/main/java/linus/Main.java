package linus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Linus using FXML.
 */
public class Main extends Application {
    private static final String FILE_PATH = "data/linus.txt";

    private Linus linus = new Linus(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            System.out.println(fxmlLoader);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLinus(linus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

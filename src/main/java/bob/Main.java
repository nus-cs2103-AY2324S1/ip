package bob;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bob using FXML.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setRoot(new AnchorPane());

            setStage(fxmlLoader, stage);
            stage.show();
            fxmlLoader.<MainWindow>getController().greetUser();
    }

    private void setStage(FXMLLoader fxmlLoader, Stage stage) {
        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bob");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            fxmlLoader.<MainWindow>getController().setBob(bob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

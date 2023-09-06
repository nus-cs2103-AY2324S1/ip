package DukeGuiControllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DukeMain extends Application {
    private Duke duke = new Duke();

    public DukeMain() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<DukeMainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            System.out.println("There was an issue accessing my nyanory :c");
        }
    }
}

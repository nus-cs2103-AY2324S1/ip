package duke.ui.graphic;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DukeApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeApplication.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            MainWindow graphicInterface = fxmlLoader.getController();
            Duke duke = new Duke(false, graphicInterface);
            graphicInterface.setDuke(duke);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

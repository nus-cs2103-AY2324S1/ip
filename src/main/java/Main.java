package seedu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private seedu.Duke duke = new seedu.Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            System.out.println(fxmlLoader);
            AnchorPane ap = fxmlLoader.load();
            System.out.println(ap);
            Scene scene = new Scene(ap);

            stage.setScene(scene);
            fxmlLoader.<ui.MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            System.out.println("sasa");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("SASasasas");
            System.out.println(e.getMessage());
        }
    }
}

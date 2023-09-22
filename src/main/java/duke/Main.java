package duke;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//@@author ruishanteo-reused
//Reused from https://github.com/ruishanteo/ip
// with minor modifications
/**
 * A GUI for the Richie application
 */
public class Main extends Application {
    private Richie richie = new Richie("src/data.txt");
    private VBox dialogContainer;
    private ScrollPane scrollPane;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Richie");
            fxmlLoader.<MainWindow>getController().setRichie(this.richie);
            stage.show();
            //dialogContainer.heightProperty().addListener((observable -> scrollPane.setV(1.0)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
//@ruishanteo

package duke.controller;

import java.io.IOException;
import java.util.Objects;

import duke.main.Cleo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Main extends Application {
    private final Cleo cleo = new Cleo("tasks.json");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String css = Objects.requireNonNull(this.getClass().getResource("/css/main.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Budget Cleo");
            stage.getIcons().add(new Image("/images/title.png"));
            scene.setFill(new LinearGradient(
                    0, 0, 1, 1, true,                      //sizing
                    CycleMethod.NO_CYCLE,                  //cycling
                    new Stop(0, Color.web("#BA4D00")),     //colors
                    new Stop(1, Color.web("#FFC800")))
            );

            stage.setMinHeight(230);
            stage.maxWidthProperty().bind(stage.widthProperty());
            stage.minWidthProperty().bind(stage.widthProperty());

            fxmlLoader.<MainWindow>getController().setCleo(cleo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

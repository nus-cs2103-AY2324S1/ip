package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

  private Duke duke = new Duke();

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
      fxmlLoader.setRoot(new AnchorPane());
      AnchorPane ap = fxmlLoader.load();

      Scene scene = new Scene(ap);
      Font.loadFont(getClass().getResourceAsStream("/styles/RobotoMono.ttf"), 16);

      scene.getStylesheets().add(Main.class.getResource("/styles/application.css").toExternalForm());
      stage.setTitle("Duke");
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

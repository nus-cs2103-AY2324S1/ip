package horo;

import java.io.IOException;

import horo.components.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Horo extends Application {

  public Horo() {
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Horo.class.getResource("/view/MainWindow.fxml"));
      AnchorPane ap = fxmlLoader.load();
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      Storage s = new Storage();

      fxmlLoader.<MainWindow>getController().setUp(s);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
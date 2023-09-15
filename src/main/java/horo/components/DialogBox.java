package horo.components;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DialogBox extends HBox {

  private static final Color BACKGROUND_COLOR = Color.web("#a2a9ae");

  @FXML
  private Label dialog;
  @FXML
  private ImageView displayPicture;

  private DialogBox(String text, Image img) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace(System.out);
    }

    dialog.setText(text);
    clipImage(img);
    this.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  private void flip() {
    this.setAlignment(Pos.TOP_LEFT);
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    FXCollections.reverse(tmp);
    this.getChildren().setAll(tmp);
  }

  public static DialogBox getUserDialog(String text, Image img) {
    return new DialogBox(text, img);
  }

  public static DialogBox getDukeDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.flip();
    return db;
  }

  private void clipImage(Image img) {
    displayPicture.setImage(img);
    Rectangle clip = new Rectangle(100, 100);
    clip.setArcWidth(10);
    clip.setArcHeight(10);
    displayPicture.setClip(clip);

    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    WritableImage newImage = displayPicture.snapshot(parameters, null);

    displayPicture.setClip(null);

    displayPicture.setEffect(new DropShadow(6, Color.BLACK));

    displayPicture.setImage(newImage);
  }
}

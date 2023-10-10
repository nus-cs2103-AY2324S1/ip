package cheese.dialogbox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public class DialogBox extends HBox {

  private Label text;
  private ImageView displayPicture;

  public DialogBox(Label l, ImageView iv) {
    text = l;
    displayPicture = iv;

    text.setWrapText(true);
    text.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
    displayPicture.setFitWidth(100.0);
    displayPicture.setFitHeight(100.0);

    this.setAlignment(Pos.TOP_RIGHT);
    this.getChildren().addAll(text, displayPicture);
  }
  
  public void flip() {
    this.setAlignment(Pos.TOP_LEFT);
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    FXCollections.reverse(tmp);
    this.getChildren().setAll(tmp);
  }

  public static DialogBox getUserDialog(Label l, ImageView iv) {
    return new DialogBox(l, iv);
  }

  public static DialogBox getCheesebotDialog(Label l, ImageView iv) {
    var db = new DialogBox(l, iv);
    db.flip();
    return db;
  }
}

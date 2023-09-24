import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox bubble;

    private DialogBox(String text, Image img, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        if (isDuke) {
            bubble.setStyle("-fx-background-color: rgba(120,164,246,0.51); -fx-background-radius: 10; -fx-padding: 5;"); // Duke color
        } else {
            bubble.setStyle("-fx-background-color: rgba(30,239,183,0.1630); -fx-background-radius: 10; -fx-padding: 5;"); // User color
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(Label textLabel, ImageView imgView) {
        return new DialogBox(textLabel.getText(), imgView.getImage(), false);
    }

    public static DialogBox getDukeDialog(Label textLabel, ImageView imgView) {
        var db = new DialogBox(textLabel.getText(), imgView.getImage(), true);
        db.flip();
        return db;
    }
}
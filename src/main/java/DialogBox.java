import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String person) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinSize(30, Double.parseDouble("-Infinity"));
        dialog.setFont(new Font("Helvetica Neue", 13));
        if (person.equals("Duke")) {
            dialog.setStyle("-fx-background-color:#f3f3f3;"
                    + "-fx-background-radius: 0 15 15 15;"
                    + "-fx-label-padding: 5 5 5 5;"
                    + "-fx-effect:dropshadow(gaussian, #949494, 10, 0.0, 0, 0);");
        } else {
            dialog.setStyle("-fx-background-color:#a9dfbf; "
                    + "-fx-background-radius: 15 0 15 15; "
                    + "-fx-label-padding: 5 5 5 5;"
                    + "-fx-effect:dropshadow(gaussian, #949494, 10, 0.0, 0, 0)");
        }
        displayPicture.setImage(img);
        displayPicture.setFitHeight(40);
        displayPicture.setFitWidth(40);
        displayPicture.setClip(new Circle(20, 20, 20));
    }

    /**![](../../../resources/images/user.png)
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox user = new DialogBox(text, img, "User");
        return user;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "Duke");
        db.flip();
        return db;
    }
}
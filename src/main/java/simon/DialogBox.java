package simon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Clip the ImageView into a circle shape
        Circle circleClip = new Circle(50, 50, 50); // Assuming the ImageView's dimensions are 100x100
        displayPicture.setClip(circleClip);

        this.setAlignment(Pos.TOP_RIGHT);
        // Add padding to the Label (text)
        HBox.setMargin(text, new Insets(0, 10, 0, 10));  // 10 units of padding to the sides of the Label
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5.0), Insets.EMPTY)));  // Setting the background color for user's dialog box
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));  // Setting the background color for Duke's dialog box
        db.flip();
        return db;
    }

}

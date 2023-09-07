package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;


/**
 * Represents the dialog boxes that pop up everytime a message is sent
 */
public class DialogBoxOld extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox instance
     * @param l Label containing text to be displayed
     * @param iv ImageView instance, which will display an image in the dialog box
     */
    public DialogBoxOld(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        text.setPadding(new Insets(10, 20, 20, 10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(50), new Insets(20, 20, 20 , 20))));

        Circle clip = new Circle(50.0); // Adjusting the radius
        clip.setCenterX(50.0);
        clip.setCenterY(50.0);
        displayPicture.setClip(clip); //clip into circle
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

    public static DialogBoxOld getUserDialog(Label l, ImageView iv) {
        return new DialogBoxOld(l, iv);
    }

    public static DialogBoxOld getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBoxOld(l, iv);
        db.flip();
        return db;
    }

}

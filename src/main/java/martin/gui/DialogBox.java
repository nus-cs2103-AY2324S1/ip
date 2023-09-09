package martin.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Represents a dialog box in the GUI for Martin. It can represent either user input or Martin's response.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box with specified label and image view.
     * 
     * @param l The label containing text for the dialog.
     * @param iv The image view displaying the avatar for the dialog.
     */
    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        text.setMinHeight(Region.USE_PREF_SIZE);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Returns a dialog box for the user with the given text and image.
     * 
     * @param text The text to be displayed in the user dialog box.
     * @param img The image to be displayed as the user's avatar.
     * @return A dialog box representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(new Label(text), new ImageView(img));
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().setAll(db.displayPicture, db.text);
        return db;
    }

    /**
     * Returns a dialog box for Martin with the given text and image.
     * 
     * @param text The text to be displayed in Martin's dialog box.
     * @param img The image to be displayed as Martin's avatar.
     * @return A dialog box representing Martin's response.
     */
    public static DialogBox getMartinDialog(String text, Image img) {
        var db = new DialogBox(new Label(text), new ImageView(img));
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}


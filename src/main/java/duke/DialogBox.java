package duke;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the Dialog box that contains the profile picture and the text.
 */
public class DialogBox extends HBox {
    private DialogBox(String textString, Image image, boolean isUser) {
        Label text = new Label(textString);
        text.setWrapText(true);
        ImageView displayPicture = new ImageView(image);
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }

    /**
     * Returns a dialog box for the user.
     * @param text The input text from the user
     * @param displayPicture The display picture of the user
     * @return The dialog box for the user
     */
    public static DialogBox getUserDialogBox(String text, Image displayPicture) {
        return new DialogBox(text, displayPicture, true);
    }

    /**
     * Returns a dialog box for the chatbot.
     * @param text The response text from the chatbot
     * @param displayPicture The display picture of the chatbot
     * @return The dialog box of the chatbot
     */
    public static DialogBox getDukeDialogBox(String text, Image displayPicture) {
        return new DialogBox(text, displayPicture, false);
    }
}

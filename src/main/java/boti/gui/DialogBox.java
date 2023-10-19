package boti.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Class for the box for dialog
 */
public class DialogBox extends HBox {

    private static final String BOTCOLOR = "gray";
    private static final String USERCOLOR = "green";
    private static final String SPACE = "    ";
    private Label text;
    private ImageView displayPicture;

    /**
     * Instantiates DialogBox
     *
     * @param l the label
     * @param iv the image view
     */

    public DialogBox(Label l, ImageView iv, String color) {
        text = l;
        displayPicture = iv;

        Font font = Font.font("Helvetica", 12);
        l.setFont(font);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Set the background color of the DialogBox to green
        this.setStyle("-fx-background-color: " + color + ";");
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    /**
     * Flips the dialog box (used for the bot)
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a new dialog box for user
     *
     * @param l the label shown
     * @param iv the image shown
     * @return a new dialog box for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(new Label(l.getText() + SPACE), iv, USERCOLOR);
    }

    /**
     * Creates a new dialog box for the bot
     *
     * @param l the label shown
     * @param iv the image shown
     * @return a new dialog box for the bot
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv, BOTCOLOR);
        db.flip();
        return db;
    }
}

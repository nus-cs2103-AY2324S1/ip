package alice.ui.components;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double DIALOG_MARGIN = 10.0;
    private static final double DIALOG_PADDING = 10.0;
    private static final double DIALOG_CORNER_RADIUS = 10.0;
    private static final String ALICE_TEXT_COLOR = "#FF69B4";
    private static final String USER_TEXT_COLOR = "#000000";
    private static final String ALICE_BACKGROUND_COLOR = "#FFFFFF";
    private static final String USER_BACKGROUND_COLOR = "#90EE90";
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
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(USER_BACKGROUND_COLOR),
            new CornerRadii(DIALOG_CORNER_RADIUS), null);
        Background background = new Background(backgroundFill);
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.dialog.setBackground(background);
        dialogBox.dialog.setTextFill(Color.valueOf(USER_TEXT_COLOR));
        dialogBox.dialog.setPadding(new Insets(DIALOG_PADDING));
        setMargin(dialogBox.dialog, new Insets(0, DIALOG_MARGIN, 0, 0));
        return dialogBox;
    }

    public static DialogBox getAliceDialog(String text, Image img) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(ALICE_BACKGROUND_COLOR),
            new CornerRadii(DIALOG_CORNER_RADIUS), null);
        Background background = new Background(backgroundFill);
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.dialog.setBackground(background);
        dialogBox.dialog.setTextFill(Color.valueOf(ALICE_TEXT_COLOR));
        dialogBox.dialog.setPadding(new Insets(DIALOG_PADDING));
        setMargin(dialogBox.dialog, new Insets(0, 0, 0, DIALOG_MARGIN));
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}

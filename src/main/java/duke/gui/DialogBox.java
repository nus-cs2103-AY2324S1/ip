package duke.gui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A dialog box consisting of an ImageView to represent the speaker's face and a label containing text from the
 * speaker.
 */
public class DialogBox extends HBox {
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
        addImageWithShadow(displayPicture, img);
    }

    /**
     * Adds a rounded shadow effect around the image in the DialogBox. Code adapted from
     * <a href="https://stackoverflow.com/a/20490028">this StackOverflow answer</a>.
     *
     * @param view The ImageView that will contain the image with the shadow.
     * @param img The image to wrap a shadow around.
     */
    private void addImageWithShadow(ImageView view, Image img) {
        view.setImage(img);

        Rectangle clip = new Rectangle(view.getFitWidth(), view.getFitHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        view.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = view.snapshot(parameters, null);

        view.setClip(null);
        view.setEffect(new DropShadow(10, Color.BLACK));

        view.setImage(image);
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

    /**
     * Sets the background of the DialogBox with a certain color.
     * @param paint The color of the background of the DialogBox to set.
     */
    private void fill(Paint paint) {
        BackgroundFill bgFill = new BackgroundFill(paint,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        setBackground(bg);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.fill(Color.SILVER);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isError) {
        DialogBox db = new DialogBox(text, img);
        db.flip();

        // set colour of response to red if there are errors
        if (isError) {
            db.dialog.setTextFill(Color.DARKRED);
        }

        return db;
    }
}

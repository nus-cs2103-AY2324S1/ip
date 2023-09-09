package remy.gui;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
//@@author SE-EDU-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
public class DialogBox extends HBox {
    private static final String USER_VIEW = "/view/UserDialogBox.fxml";
    private static final String REMY_VIEW = "/view/RemyDialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, String view) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(view));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, USER_VIEW);
    }

    public static DialogBox getRemyDialog(String text) {
        return new DialogBox(text, REMY_VIEW);
    }
}
//@@author
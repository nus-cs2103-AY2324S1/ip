package didier.controller;

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
import javafx.scene.text.Font;

/**
 * Custom controller that contains the dialog and display picture of the current locuteur.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setFont(new Font(14.0));
        displayPicture.setImage(image);

        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right,
     * and the dialog background color is pink.
     */
    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        this.getChildren().setAll(temp);
        this.setAlignment(Pos.TOP_LEFT);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public static DialogBox getUserDialog(String input, Image userImage) {
        return new DialogBox(input, userImage);
    }

    public static DialogBox getDidierDialog(String response, Image didierImage) {
        DialogBox dialogBox = new DialogBox(response, didierImage);
        dialogBox.flip();
        return dialogBox;
    }
}

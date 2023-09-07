package duke;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
//import javafx.scene.shape.Circle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
//
//import java.awt.*;
//
//public class DialogBox extends HBox {
//
//    private Label text;
//    private ImageView displayPicture;
//
//    public DialogBox(Label l, ImageView iv, Color bgColor) {
//        text = l;
//        displayPicture = iv;
//
//        //Size of image and dialog properties
//        displayPicture.setFitWidth(100.0);
//        displayPicture.setFitHeight(100.0);
//        text.setWrapText(true);
//
//        //Circle Clip for displayPicture
//        Circle clipCircle = new Circle(50);
//        displayPicture.setClip(clipCircle);
//        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
//        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);
//
//        // Padding and Setting for DialogBox
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.setPadding(new Insets(10)); //Padding around DialogBox
//        this.getChildren().addAll(text, displayPicture);
//
//        // Add margins to the text and displayPicture elements
//        HBox.setMargin(text, new Insets(5, 10, 5, 10)); // Adjust the values as needed
////        HBox.setMargin(displayPicture, new Insets(5, 10, 5, 10)); // Adjust the values as needed
//
//        //Sets background for circle+text
////        this.setBackground(new Background(new BackgroundFill(bgColor, null, null)));
//
//        //Background for text-part only
//        this.text.setBackground(new Background(new BackgroundFill(bgColor, null, null)));
//    }
//
//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        this.setAlignment(Pos.TOP_LEFT);
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        FXCollections.reverse(tmp);
//        this.getChildren().setAll(tmp);
//    }
//
//    public static DialogBox getUserDialog(Label l, ImageView iv) {
//        return new DialogBox(l, iv, Color.CYAN);
//    }
//
//    public static DialogBox getDukeDialog(Label l, ImageView iv) {
//        var db = new DialogBox(l, iv, Color.YELLOW);
//        db.flip();
//        return db;
//    }
//}

import java.awt.*;
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

        //Size of image and dialog properties
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
//        dialog.setWrapText(true);

        //Circle Clip for displayPicture
        Circle clipCircle = new Circle(50);
        displayPicture.setClip(clipCircle);
        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);

        // Padding and Setting for DialogBox
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10)); //Padding around DialogBox

        // Add margins to the text and displayPicture elements
//        HBox.setMargin(dialog, new Insets(5, 10, 5, 10)); // Adjust the values as needed
//        HBox.setMargin(displayPicture, new Insets(5, 10, 5, 10)); // Adjust the values as needed

        //Sets background for circle+text
//        this.setBackground(new Background(new BackgroundFill(bgColor, null, null)));

        //Background for text-part only
        this.dialog.setBackground(new Background(new BackgroundFill(Color.CYAN, null, null)));
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}



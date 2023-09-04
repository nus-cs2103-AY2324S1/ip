package rat.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    @FXML
    private Label time;

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
        time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        for (Node n : tmp) {
            if (n instanceof Label) {
                if (Objects.equals(n.getId(), "text-bubble")) {
                    ((Label) n).setAlignment(Pos.CENTER_LEFT);
                    n.setStyle("-fx-background-color: #ffffff;"
                            + "-fx-background-radius: 20;"
                            + "-fx-padding: 15;"
                            + "-fx-font-family: 'SF Pro Display Regular';"
                    );
                }
                if (Objects.equals(n.getId(), "time")) {
                    ((Label) n).setAlignment(Pos.BOTTOM_LEFT);
                    n.setStyle("-fx-text-fill: #ffffff;"
                            + "-fx-font-size: 10;"
                            + "-fx-font-family: 'SF Pro Display Light';"
                    );
                    n.setTranslateX(-5);
                }
            }
        }
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getRatDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

package duke.ui;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Ellipse imageClip;

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

        imageClip.setFill(new ImagePattern(img));
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image iv) {
        return new DialogBox(text, iv);
    }

    public static DialogBox getDukeDialog(String text, Image iv) {
        var db = new DialogBox(text, iv);
        db.flip();
        return db;
    }
}

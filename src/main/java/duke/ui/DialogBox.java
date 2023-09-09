package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private Ellipse imageClip;

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        imageClip = new Ellipse();

        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        imageClip.setCenterX(50);
        imageClip.setCenterY(50);
        imageClip.setRadiusX(40);
        imageClip.setRadiusY(50);
        imageClip.setSmooth(true);

        displayPicture.setClip(imageClip);

        this.setSpacing(5.0);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

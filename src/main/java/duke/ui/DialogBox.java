package duke.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private static final Image user = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image duke = new Image(DialogBox.class.getResourceAsStream("/images/DaDuke.png"));

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(getDialogLabel(text), new ImageView(user));
    }

    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(getDialogLabel(text), new ImageView(duke));
    }

    private static Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

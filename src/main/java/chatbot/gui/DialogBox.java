package chatbot.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    public static final double IMAGE_HEIGHT = 50.0;
    public static final double IMAGE_WIDTH = 50.0;
    private Label text;
    private ImageView profileImage;

    public DialogBox(Label label, ImageView image) {
        text = label;
        profileImage = image;

        text.setWrapText(true);
        profileImage.setFitHeight(IMAGE_HEIGHT);
        profileImage.setFitWidth(IMAGE_WIDTH);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.profileImage);
    }

}

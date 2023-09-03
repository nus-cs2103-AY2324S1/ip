package shiba.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Represents a dialog node in the dialog box, which can be directly added to a parent.
 */
public class DialogNode extends HBox {
    private static final Image USER_IMAGE = new Image(DialogNode.class.getResourceAsStream("/images/user.jpg"));
    private static final Image SHIBA_IMAGE = new Image(DialogNode.class.getResourceAsStream("/images/shiba.png"));
    private static final Font DIALOG_FONT = new Font("Comic Sans MS", 18);
    private static final int IMAGE_SIZE = 150;
    private static final int LABEL_WIDTH = 410;
    private static final int SPACING = 10;

    /**
     * Constructor for DialogNode
     *
     * @param text Text to be displayed
     * @param isUser Whether the dialog node is from the user - determines the image that will be displayed
     */
    public DialogNode(String text, boolean isUser) {
        super(SPACING);

        ImageView imageView = new ImageView(isUser ? USER_IMAGE : SHIBA_IMAGE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setFitWidth(IMAGE_SIZE);

        Label label = new Label(text);
        label.setMinWidth(LABEL_WIDTH);
        label.setMaxWidth(LABEL_WIDTH);
        label.setWrapText(true);
        label.setFont(DIALOG_FONT);

        if (isUser) {
            label.setPadding(new Insets(0, 0, 0, SPACING));
            getChildren().addAll(label, imageView);
        } else {
            label.setPadding(new Insets(0, SPACING, 0, 0));
            getChildren().addAll(imageView, label);
        }
    }
}

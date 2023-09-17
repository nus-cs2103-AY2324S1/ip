package shiba.ui.components;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Represents a dialog node in the dialog box, which can be directly added to a parent.
 */
public class DialogNode extends HBox {
    private static final Image USER_IMAGE = new Image(DialogNode.class.getResourceAsStream("/images/user.jpg"));
    private static final Image SHIBA_IMAGE = new Image(DialogNode.class.getResourceAsStream("/images/shiba.png"));
    private static final Font DIALOG_FONT = new Font("Comic Sans MS", 18);
    private static final int IMAGE_SIZE = 100;
    private static final int TEXT_PORTION_WIDTH = 475;
    private static final int HORIZONTAL_SPACING = 10;
    private static final int VERTICAL_SPACING = 5;

    /**
     * Represents a sub-node inside this DialogNode
     */
    public static class SubNode {
        public static final int INDENTATION_WIDTH = 20;
        private static final int MAX_INDENTATION_LEVEL = TEXT_PORTION_WIDTH / 2 / INDENTATION_WIDTH;

        private final int indentationLevel;
        private final String text;

        /**
         * Constructor for SubNode
         *
         * @param indentationLevel Indentation level of the sub-node, capped at a maximum value
         * @param text Text to be displayed
         */
        public SubNode(int indentationLevel, String text) {
            this.indentationLevel = Math.min(indentationLevel, MAX_INDENTATION_LEVEL);
            this.text = text;
        }
    }

    /**
     * Constructor for DialogNode
     *
     * @param isUser Whether the dialog node is from the user - determines the image that will be displayed
     * @param textNodes List of sub-nodes to be displayed
     */
    public DialogNode(boolean isUser, ArrayList<SubNode> textNodes) {
        super(HORIZONTAL_SPACING);

        ImageView imageView = new ImageView(isUser ? USER_IMAGE : SHIBA_IMAGE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setFitWidth(IMAGE_SIZE);

        VBox vbox = new VBox(VERTICAL_SPACING);
        HBox.setHgrow(vbox, Priority.ALWAYS);

        if (isUser) {
            getChildren().addAll(vbox, imageView);
        } else {
            getChildren().addAll(imageView, vbox);
        }

        for (SubNode textNode : textNodes) {
            Label textLabel = new Label(textNode.text);
            textLabel.setWrapText(true);
            textLabel.setFont(DIALOG_FONT);
            textLabel.setPadding(new Insets(0, 0, 0, textNode.indentationLevel * SubNode.INDENTATION_WIDTH));
            textLabel.setMaxWidth(Double.MAX_VALUE);
            if (isUser) {
                textLabel.setAlignment(Pos.CENTER_RIGHT);
            }
            vbox.getChildren().add(textLabel);
        }
    }
}

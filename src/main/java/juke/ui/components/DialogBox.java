package juke.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import juke.ui.Ui;

/**
 * Abstract class that is used to represent a dialog box that is
 * displayed to the user through the GUI.
 */
public abstract class DialogBox extends HBox {
    /** Height of the image. */
    private static final double IMAGE_HEIGHT = 100.0d;

    /** Width of the image. */
    private static final double IMAGE_WIDTH = 100.0d;

    /** Radius of the edges of the dialog box. */
    private static final double BOX_RADIUS = 20.0d;

    /** Container for the dialog box contents. */
    @FXML
    private StackPane dialogBubble;

    /** Background of the dialog box contents. */
    @FXML
    private Region background;

    /** Text within the dialog box. */
    @FXML
    private Label textLabel;

    /** Profile image to display in the chat. */
    @FXML
    private ImageView displayImage;

    /**
     * Constructor for {@code DialogBox}.
     *
     * @param text Text to render in the dialog box
     * @param image Profile image
     */
    protected DialogBox(String text, Image image) {
        this.textLabel = new Label(text);
        this.displayImage = new ImageView(image);

        // allow text to be wrapped if its exceeds the width of the dialog box
        this.textLabel.setWrapText(true);

        // set the image size
        this.displayImage.setFitHeight(DialogBox.IMAGE_HEIGHT);
        this.displayImage.setFitWidth(DialogBox.IMAGE_WIDTH);

        // clip the image into a circle
        Circle clippingCircle = new Circle();
        clippingCircle.setCenterX(DialogBox.IMAGE_WIDTH / 2);
        clippingCircle.setCenterY(DialogBox.IMAGE_HEIGHT / 2);
        clippingCircle.setRadius(Math.min(DialogBox.IMAGE_WIDTH, DialogBox.IMAGE_HEIGHT) / 2);
        this.displayImage.setClip(clippingCircle);

        // create a rectangle container to store the contents of the dialog box
        this.background = new Region();
        this.background.setBackground(new Background(
                new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(20.0d), Insets.EMPTY)));
        this.background.setMaxWidth(Ui.WINDOW_WIDTH - DialogBox.IMAGE_WIDTH);
        this.background.maxHeightProperty().bind(this.textLabel.heightProperty().add(25.0d));
        this.background.minHeightProperty().bind(this.textLabel.heightProperty().add(25.0d));
        this.background.minWidthProperty().bind(this.textLabel.widthProperty().add(25.0d));
        this.background.maxWidthProperty().bind(this.textLabel.widthProperty().add(25.0d));

        // Create the stackpane to display both items
        this.dialogBubble = new StackPane(this.background, this.textLabel);

        // set the aligment of the contents of dialog box
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(
                this.dialogBubble,
                this.displayImage);

        // set the padding for the entire dialog box
        this.setPadding(new Insets(10, 5, 10, 5));
        this.setSpacing(10);
    }

    /**
     * Inverts the order of the widgets within this dialog box.
     */
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> childWidgets = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(childWidgets);
        this.getChildren().setAll(childWidgets);
    }

    /**
     * Creates a dialog box that represents the user's inputs.
     *
     * @param text Text to render in the dialog box
     * @return {@code UserDialog} object
     */
    public static DialogBox ofUser(String text) {
        return new UserDialog(text);
    }

    /**
     * Creates a dialog box that represents Juke's outputs.
     *
     * @param text Text to render in the dialog box
     * @return {@code JukeDialog} object
     */
    public static DialogBox ofJuke(String text) {
        return new JukeDialog(text);
    }

    /**
     * Sets the color of the dialog box. Used to differentiate
     * dialog boxes between the user and Juke.
     *
     * @param color Color to set the dialog box to
     */
    protected void setDialogBubbleColor(Color color) {
        this.background.setBackground(
                new Background(new BackgroundFill(
                        color, new CornerRadii(DialogBox.BOX_RADIUS), Insets.EMPTY)));
    }
}

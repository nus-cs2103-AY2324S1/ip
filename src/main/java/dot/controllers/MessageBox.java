package dot.controllers;

import java.io.IOException;
import java.util.ArrayList;

import dot.errors.TaskError;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * MessageBox is a dynamic HBox that can receive
 * a number of nodes, and format them to the needs
 * of a message.
 * <p>
 * MessageBox is currently only able to support
 * ImageViews and Labels, and 1 of each only
 */
public class MessageBox extends HBox {
    @FXML
    private Label messageText;
    @FXML
    private ImageView messageImage;

    private MainWindow mainWindowInstance;

    /**
     * Constructor for custom component MessageBox.
     *
     * @param alignment      This is the alignment of Nodes within MessageBox.
     * @param textBackground This is the Background for the MessageBox.
     * @param nodeDetails    These are the nodes contaned within the MessageBox,
     *                       and can be stated in any order.
     */
    public MessageBox(Pos alignment, Background textBackground, MainWindow m,
                      Object... nodeDetails) {
        mainWindowInstance = m;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.err.println(TaskError.ERR_READING_FXML.getFullErrorMessage(e));
        }

        this.setAlignment(alignment);
        ArrayList<Node> nodes = new ArrayList<>();
        for (Object o : nodeDetails) {
            if (o instanceof String) {
                String message = (String) o;
                messageText.setText(message);
                messageText.setBackground(textBackground);
                nodes.add(messageText);
            } else if (o instanceof Image) {
                Image image = (Image) o;
                messageImage.setImage(image);
                nodes.add(messageImage);
            }
        }
        this.getChildren().setAll(nodes);
    }

    /**
     * Initializes upon show, and clips the messageImage into a circle.
     */
    @FXML
    public void initialize() {
        double diameter = 95.0;
        Circle mask = new Circle(diameter / 2);
        mask.setCenterX(diameter / 2);
        mask.setCenterY(diameter / 2);
        messageImage.setClip(mask);

        // Set binding for MessageBox width to follow that of MainWindow (AnchorPane)
        this.prefWidthProperty().bind(mainWindowInstance.widthProperty());

        // Set binding for Message box font size to be multiplied by the extent
        // which MainWindow is enlarged by, capped at 2
        messageText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double defaultFontSize = 13.0;
            double defaultMainWindowWidth = 400;
            double mainWindowWidth = mainWindowInstance.getWidth();
            double maxMultiplier = 2.0;
            double multiplier = mainWindowWidth / defaultMainWindowWidth < 1
                    ? 1
                    : Math.min(mainWindowInstance.getWidth() / defaultMainWindowWidth, maxMultiplier);
            return new Font("Baskerville", defaultFontSize * multiplier);
        }, mainWindowInstance.widthProperty()));
    }

}

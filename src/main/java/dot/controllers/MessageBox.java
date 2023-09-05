package dot.controllers;

import java.io.IOException;
import java.util.ArrayList;

import dot.errors.TaskError;
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

    /**
     * Constructor for custom component MessageBox.
     *
     * @param alignment      This is the alignment of Nodes within MessageBox.
     * @param textBackground This is the Background for the MessageBox.
     * @param nodeDetails    These are the nodes contaned within the MessageBox,
     *                       and can be stated in any order.
     */
    public MessageBox(Pos alignment, Background textBackground, Object... nodeDetails) {
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
    }

}

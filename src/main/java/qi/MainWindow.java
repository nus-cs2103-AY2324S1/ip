package qi;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Qi qi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image qiImage = new Image(this.getClass().getResourceAsStream("/images/Qi.png"));

    /**
     * Sets the width and the height property of the dialogContainer to scale
     * accordingly with the scrollPane. Sets the scrollPane to automatically
     * scroll down to the latest messages
     */
    @FXML
    public void initialize() {
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().multiply(0.96));
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setQi(Qi qi) {
        dialogContainer.getChildren().add(DialogBox.getQiDialog(qi.showWelcome(), qiImage));
        BackgroundSize backgroundSize = new BackgroundSize(900,
                700,
                true,
                true,
                true,
                false);
        dialogContainer.setBackground(new Background(new BackgroundImage(
                new Image(this.getClass().getResourceAsStream("/images/BackGround.png")),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize)));
        this.qi = qi;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = qi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getQiDialog(response, qiImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }
}


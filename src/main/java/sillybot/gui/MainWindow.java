package sillybot.gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sillybot.SillyBot;
import sillybot.Ui;

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

    private SillyBot sillyBot;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image dukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/sillyBot.png")));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String welcomeMessage = Ui.showWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    public void setDuke(SillyBot s) {
        sillyBot = s;
    }

    /**
     * Creates two dialog boxes, one echoing user.png input and the other containing SillyBot's reply and then appends
     * them to the dialog container. Clears the user.png input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sillyBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

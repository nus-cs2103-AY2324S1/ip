package gbot;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private GBot gbot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private final Image gbotImage = new Image(this.getClass().getResourceAsStream("/images/GBotImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello I'm GBot.\nHow may I assist you today?", gbotImage)
        );
    }

    public void setGBot(GBot g) {
        gbot = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, gbotImage)
        );
        userInput.clear();
    }
}

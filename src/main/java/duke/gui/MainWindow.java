package duke.gui;

import java.util.Objects;

import duke.Duke;
import duke.Ui;
import duke.parser.UserInputParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Duke duke;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Add an initial message when the GUI first opens
        String initialMessage = new Ui().getWelcomeMessage();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(initialMessage, dukeImage));
    }

    /**
     * Sets the Duke object.
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        // Note: will not show ByeMessage if user input is "bye"
        if (!UserInputParser.getIsActive()) {
            System.exit(0);
        }
    }
}

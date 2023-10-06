package seedu.duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.duke.Duke;

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

    private Ui ui;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Lemon.png"));

    /**
     * Initialise the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ui = new Ui();
        displayGreeting();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one as the user and capturing input
     * and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                Ui.DialogBox.getUserDialog(input, userImage),
                Ui.DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();
    }

    /**
     * Displays the welcome message in a dialog box when Lemon is activated.
     */
    public void displayGreeting() {
        Ui.DialogBox greeting = Ui.DialogBox.getDukeDialog(ui.welcomeMessage(), dukeImage);
        dialogContainer.getChildren().add(greeting);
    }
}

package duke;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class represents the main graphical user interface for the Duke chatbot application.
 *
 * @author selwyn
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpeg"));

    /**
     * Initializes the MainWindow by binding the scrollPane's vvalue property to the height property of dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke instance for this MainWindow.
     *
     * @param d The Duke instance to set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles user input when the user enters hir or her input.
     * Retrieves the user's input, gets a response from Duke, and displays it in the dialogContainer.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Prints the initial greeting message from Duke in the dialogContainer.
     */
    public void printGreeting() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.printGreet(), dukeImage));
    }
}

package graphicaluserinterface;

import duke.Duke;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final Image IMAGE_DUKE = new Image(MainWindow.class.getResourceAsStream("/images/DaDuke.png"));
    private static final Image IMAGE_USER = new Image(MainWindow.class.getResourceAsStream("/images/DaUser.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private String response = "";

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.MESSAGE_WELCOME, IMAGE_DUKE));
    }

    /**
     * Sets the Duke object.
     *
     * @param d The Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the response from Duke.
     *
     * @param response The response from Duke.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Prints a message from Duke.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, IMAGE_DUKE));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        this.duke.run(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, IMAGE_USER),
                DialogBox.getDukeDialog(response, IMAGE_DUKE)
        );
        userInput.clear();

        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}

package duke;

import java.util.ArrayList;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Deals with users input and clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ArrayList<String> response = duke.getResponse(input);
        printUserReply(input);
        for (Object res : response.toArray()) {
            printDukeReply((String) res);
        }
        userInput.clear();
    }


    /**
     * Displays users reply on screen.
     */
    @FXML
    public void printUserReply(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Displays Duke's reply on screen.
     */
    @FXML
    public void printDukeReply(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }
}

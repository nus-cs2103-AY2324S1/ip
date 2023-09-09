package uicomponents;

import crackerpackage.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.concurrent.*;

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

    private Cracker duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chuck2.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/chuck1.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Cracker d) {
        duke = d;
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        String response = duke.getResponse(input);
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        DialogBox duke = DialogBox.getDukeDialog(response, dukeImage);
        user.setMinHeight(Region.USE_PREF_SIZE);
        duke.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                user,
                duke
        );

        if(response.startsWith("Bye!")){
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        userInput.clear();
    }
}

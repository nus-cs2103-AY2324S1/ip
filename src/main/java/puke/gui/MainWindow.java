package puke.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import puke.Puke;
import puke.command.ErrorCommand;
import puke.command.ExitCommand;

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

    private Puke puke;
    private boolean isExit = false;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image pukeImage = new Image(this.getClass().getResourceAsStream("/images/Puke.png"));

    /**
     * Initializes the Main Window
     */
    @FXML
    public void initialize() {
        assert (userImage != null);
        assert (pukeImage != null);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(PukeDialogBox.startup(pukeImage));
    }

    public void setPuke(Puke p) {
        puke = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Puke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = puke.getResponse(input);
        dialogContainer.getChildren().add(UserDialogBox.getUserDialog(input, userImage));
        if (isExit) {
            Platform.exit();
        }
        if (response.equals(ExitCommand.EXIT_MESSAGE)) {
            isExit = true;
            dialogContainer.getChildren().add(PukeDialogBox.getPukeDialog(response, pukeImage));
        } else if (response.equals(ErrorCommand.getErrorMessage())) {
            dialogContainer.getChildren().add(ErrorDialogBox.getErrorDialog(pukeImage));
        } else {
            dialogContainer.getChildren().add(PukeDialogBox.getPukeDialog(response, pukeImage));
        }
        userInput.clear();
    }
}

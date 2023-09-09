package remy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import remy.Remy;
import remy.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
//@@author SE-EDU-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Remy remy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User-icon.png"));
    private Image remyImage = new Image(this.getClass().getResourceAsStream("/images/Remy-icon.png"));

    /**
     * Initializes MainWindow and displays the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getRemyDialog(Ui.getWelcomeMessage())
        );
    }

    public void setRemy(Remy r) {
        this.remy = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Remy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Stops accepting user input once Exit Command has been triggered.
     */
    @FXML
    private void handleUserInput() {
        if (!remy.hasExited()) {
            String input = userInput.getText();
            String response = remy.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getRemyDialog(response)
            );
            userInput.clear();
        }
    }
}
//@@author
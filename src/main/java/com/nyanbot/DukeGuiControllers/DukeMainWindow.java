package com.nyanbot.DukeGuiControllers;

import com.nyanbot.DukeGuiElements.DukeDialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Tan Kerway
 */
public class DukeMainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/golfing.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/nyan.png")));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DukeDialogBox.getDukeDialog(duke.getGreeting(), dukeImage)
        );
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getUserResponse(input);
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DukeDialogBox.getUserDialog(response, dukeImage)
            );
            Platform.exit();
            userInput.clear();
            return;
        }
        DukeDialogBox userDialog = DukeDialogBox.getUserDialog(input, userImage);
        DukeDialogBox dukeDialog = DukeDialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
    }
}
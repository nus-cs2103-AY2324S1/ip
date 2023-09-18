package sae;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sae.util.Ui;

import java.util.Objects;

/**
 * Represents the controller for MainWindow, providing the layout for other controls.
 * This class is reused and adapted
 * from this <a href="https://se-education.org/guides/tutorials/javaFx.html"> tutorial.
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

    private Sae sae;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png"))
    );
    private final Image saeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Sae.png"))
    );

    /**
     * Initializes the GUI layout and components.
     * This method is called when the FXML file is loaded.
     * It binds the scroll pane to the height of the dialog container
     * and displays an initial greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Add an initial message when the GUI first opens
        String initialMessage = new Ui().greetUser();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(DialogBox.getSaeDialog(initialMessage, saeImage));
    }

    /**
     * Sets the Sae object for interaction.
     *
     * @param s The Sae object.
     */
    public void setSae(Sae s) {
        sae = s;
    }

    /**
     * Handles user input, generates responses, and updates the dialog container.
     * It creates two dialog boxes, one for user input and one for Sae's reply,
     * and appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (userInput.equals("bye")) {
        }
        String response = sae.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSaeDialog(response, saeImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit(); // Close the JavaFX application
        }
    }
}

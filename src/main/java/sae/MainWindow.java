package sae;
import java.util.Objects;

import sae.Sae;
import sae.util.Ui;
import sae.util.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.application.Platform;


/**
 * Represents controller for MainWindow. Provides the layout for the other controls.
 * Reused from this <a href="https://se-education.org/guides/tutorials/javaFx.html">tutorial</a>
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
     * Initializes the GUI.
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
     * Sets the Sae object.
     * @param s Sae object.
     */
    public void setSae(Sae s) {
        sae = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
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
        //Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit(); // Close the JavaFX application
        }
    }

}

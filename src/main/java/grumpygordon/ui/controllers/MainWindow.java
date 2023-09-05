package grumpygordon.ui.controllers;

import grumpygordon.GrumpyGordon;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * Introductory message from Grumpy Gordon.
     */
    private static final String INTRO = "Oi! I'm Grumpy Gordon. Why are you bothering me?";
    /**
     * Scroll pane for dialog container.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * Container for dialog.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * User input.
     */
    @FXML
    private TextField userInput;
    /**
     * Grumpy Gordon instance.
     */
    @FXML
    private GrumpyGordon grumpyGordon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image gordonImage = new Image(this.getClass().getResourceAsStream("/images/gordonImage.png"));
    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getGordonDialog(INTRO, gordonImage));
    }
    /**
     * Sets Grumpy Gordon instance.
     * @param gg Grumpy Gordon instance
     */
    public void setGrumpyGordon(GrumpyGordon gg) {
        grumpyGordon = gg;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String userText = userInput.getText();
        String gordonText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getGordonDialog(gordonText, gordonImage)
        );
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        return grumpyGordon.getResponse(input);
    }
}

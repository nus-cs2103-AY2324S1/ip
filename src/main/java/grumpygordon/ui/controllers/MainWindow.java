package grumpygordon.ui.controllers;

import grumpygordon.GrumpyGordon;
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

    private GrumpyGordon grumpyGordon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image gordonImage = new Image(this.getClass().getResourceAsStream("/images/gordonImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

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
        return "Duke heard: " + input;
    }
}

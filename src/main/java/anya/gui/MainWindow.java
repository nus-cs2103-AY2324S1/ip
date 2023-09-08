package anya.gui;

import anya.Anya;
import anya.messages.Messages;
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

    private Anya anya;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/damian.png"));
    private Image anyaImage = new Image(this.getClass().getResourceAsStream("/images/anya.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAnya(Anya a) {
        anya = a;
        dialogContainer.getChildren().add(
                DialogBox.getAnyaDialog(Messages.MESSAGE_GREETING, anyaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = anya.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAnyaDialog(response, anyaImage)
        );
        userInput.clear();
    }
}

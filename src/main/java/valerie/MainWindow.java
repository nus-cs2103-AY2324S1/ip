package valerie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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

    private Valerie valerie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image valerieImage = new Image(this.getClass().getResourceAsStream("/images/Valerie.jpg"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getValerieDialog("Hi! I'm Valerie. What can I do for you today?", valerieImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setValerie(Valerie v) {
        valerie = v;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // User's response
        String input = userInput.getText();
        ArrayList<String> responses = valerie.getResponse(input);

        // Valerie's response
        StringBuilder sb = new StringBuilder();
        for (String response : responses) {
            sb.append(response);
            sb.append("\n");
        }
        String fullResponse = sb.toString();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage), // User's response
                DialogBox.getValerieDialog(fullResponse, valerieImage) // Valerie's response
        );

        userInput.clear();
    }

}


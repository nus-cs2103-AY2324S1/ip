package corgi.ui;

import corgi.Corgi;
import javafx.application.Platform;
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

    private Corgi corgi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image corgiImage = new Image(this.getClass().getResourceAsStream("/images/corgi.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCorgi(Corgi c) {
        corgi = c;
        dialogContainer.getChildren().add(
                DialogBox.getCorgiDialog(corgi.getIntro(), corgiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.equals("")) {
            return;
        }

        String response = corgi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCorgiDialog(response, corgiImage)
        );

        String lowerCaseInput = input.toLowerCase();

        if (lowerCaseInput.equals("bye")) {
            Platform.runLater(() -> {
                handleByeCommand();
            });
        }

        userInput.clear();
    }

    private void handleByeCommand() {
        Platform.exit();
    }
}


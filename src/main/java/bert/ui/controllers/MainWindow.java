package bert.ui.controllers;

import bert.Bert;
import bert.commands.CommandResult;
import bert.common.Messages;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Bert bert;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bertImage = new Image(this.getClass().getResourceAsStream("/images/DaBert.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBert(Bert bert) {
        this.bert = bert;
        dialogContainer.getChildren().add(DialogBox.getBertDialog(Messages.MESSAGE_WELCOME, bertImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bert's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        CommandResult commandResult = bert.handleInput(input.trim());
        String response = commandResult.getFeedbackToUser();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBertDialog(response, bertImage)
        );
        userInput.clear();

        if (response.equals(Messages.MESSAGE_GOODBYE)) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
    }
}

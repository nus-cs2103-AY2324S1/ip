package minion.ui;

import java.io.IOException;

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
import minion.Minion;
import minion.commands.CommandResult;
import minion.common.Messages;
import minion.data.exception.MinionException;

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

    private Minion minion;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Gru.png"));
    private final Image minionImage = new Image(this.getClass().getResourceAsStream("/images/Minion.png"));

    private Stage mainStage;

    /**
     * Initialise the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(Messages.MESSAGE_WELCOME, minionImage)
        );
    }

    public void setupChatbot(Minion minion, Stage stage) {
        this.minion = minion;
        this.mainStage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult result = null;
        String response = null;
        try {
            result = minion.getCommandResult(input);
            response = result.getFeedbackToUser();
        } catch (IOException | MinionException e) {
            response = e.getMessage();
        } finally {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, minionImage)
            );
            if (result != null && result.isExit()) {
                handleExit();
            }
            userInput.clear();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> mainStage.close());
        delay.play();
    }
}

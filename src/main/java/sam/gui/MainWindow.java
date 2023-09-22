package sam.gui;

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
import sam.Sam;
import sam.commands.CommandResult;
import sam.constants.Message;

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
    private Stage stage;
    private Sam sam;
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image SAM_IMAGE = new Image(this.getClass().getResourceAsStream("/images/sam.gif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getSamDialog(Message.WELCOME, SAM_IMAGE)
        );
    }

    public void setSam(Sam d, Stage stage) {
        this.sam = d;
        this.stage = stage;
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
            result = sam.getResult(input);
            response = result.getFeedback();
        } catch (Exception e) {
            response = e.getMessage();
        } finally {
            assert response != null : "No response message returned from Sam";

            userInput.clear();
            if (result == null) {
                dialogContainer.getChildren().add(
                        DialogBox.getUserDialog(input, USER_IMAGE)
                );
                handleExit();
                return;
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, USER_IMAGE),
                    DialogBox.getSamDialog(response, SAM_IMAGE)
            );
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        dialogContainer.getChildren().add(
                DialogBox.getSamDialog(Message.BYE, SAM_IMAGE)
        );
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> this.stage.close());
        delay.play();
    }
}

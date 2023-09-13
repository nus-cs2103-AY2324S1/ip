package duke.ui;

import duke.Duke;
import duke.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Tony.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.png"));

    @FXML
    public void initialize() {

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    public void setDuke(Duke d) {

        duke = d;
    }

    public void printIntroduction() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert duke != null : "Duke should be initialized before handling user input!";
        assert userInput != null : "userInput should be initialized!";
        assert dialogContainer != null : "dialogContainer should be initialized!";

        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

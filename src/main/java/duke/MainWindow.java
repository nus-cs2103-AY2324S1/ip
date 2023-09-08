package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        handleBotInput();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(userImage)),
                DialogBox.getDukeDialog(new Label(response), new ImageView(dukeImage))
        );
        userInput.clear();
    }

    @FXML
    private void handleBotInput() {
        String response = "Hello! I'm Oranges.\n" + "What can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(response), new ImageView(dukeImage))
        );
    }

}

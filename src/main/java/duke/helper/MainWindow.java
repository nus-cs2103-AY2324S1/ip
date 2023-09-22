package duke.helper;

import duke.MeowBot;
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
    @FXML
    private Button sendButton;

    private MeowBot duke;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/stickerduke.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/sticker.png"));

    /**
     * Initialie the main window for users to interact with
     */

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.greet()), new ImageView(dukeImage)));
    }

    public void setDuke(MeowBot d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input, the other containing MeowBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Label lInput = new Label(input);
        String response = duke.getResponse(input);
        Label lResponse = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(lInput, new ImageView(userImage)),
                DialogBox.getDukeDialog(lResponse, new ImageView(dukeImage))
        );
        userInput.clear();
    }
}

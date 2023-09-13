package duke.Controller;

import duke.Duke;
import duke.Ui.Ui;

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
    @FXML
    private TextField Intro;

    private Duke duke;

    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    private boolean isIntroDone = false;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        handleUserInput();
        isIntroDone = true;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (!isIntroDone){
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(duke.intro(), dukeImage)
            );
            return;
        } else {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(ui.printChat(input), userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            if (input.equalsIgnoreCase("bye")){
                Platform.exit();
            }
            userInput.clear();
        }
    }
}

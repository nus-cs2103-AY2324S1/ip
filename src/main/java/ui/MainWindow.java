package ui;

import commands.Command;
import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/blue.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/red.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(new DialogBox("Hello! I'm Red\nWhat can I do for you?",
                dukeImage, 1));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public String getResponse(String input) {
        return input;
    }

    @FXML
    public String getUserInput() {
        return userInput.getText();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String command = userInput.getText();
            Command c = new Parser().getCommand(command);
            String result = c.execute(duke.getTaskList(), duke.getDataFile());
            String input = userInput.getText();
            String response = getResponse(result);
            dialogContainer.getChildren().addAll(
                    new DialogBox(input, userImage, 0),
                    new DialogBox(response, dukeImage, 1)
            );
            userInput.clear();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            dialogContainer.getChildren().addAll(
                    new DialogBox(userInput.getText(), userImage, 0),
                    new DialogBox(e.getMessage(), dukeImage, 1)
            );
            userInput.clear();
        }
    }


}

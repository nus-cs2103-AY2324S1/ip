package chatterbot;

import chatterbot.Main;
import chatterbot.data.Task;
import chatterbot.data.TaskList;

import java.util.ArrayList;

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

    private ChatterBot chatterbot;
    private ArrayList<Task> list;
    private TaskList taskList;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dukeImage.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getChatterBotDialog("Hello! I'm ChatterBot.\nPlease enter a command.", dukeImage)
        );
    }

    public void setChatterBot(ChatterBot c, TaskList taskList) {
        chatterbot = c;
        this.taskList = taskList;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        System.out.println("a");
        String input = userInput.getText();
        System.out.println("b");
        String response = chatterbot.getResponse(input);
        System.out.println("c");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatterBotDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
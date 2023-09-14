package rayshawn.chatbot.ui;

import static rayshawn.chatbot.messages.Messages.WELCOME_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rayshawn.chatbot.commands.ByeCommand;
import rayshawn.chatbot.commands.Command;
import rayshawn.chatbot.commands.CommandResult;
import rayshawn.chatbot.commands.Logic;
import rayshawn.chatbot.tasks.Task;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Logic logic;

    /**
     * This is to initnialise the start of the chatbot GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage));
        logic = new Logic();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        Command command = logic.getCommand(input);

        getResponse(command);

        if (ByeCommand.isBye(command)) {
            exitApplication();
        }

        userInput.clear();
    }

    private void getResponse(Command command) {
        try {
            CommandResult commandResult = logic.executeCommand(command);
            showResultToUser(commandResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showToUser(String... message) {
        for (String m : message) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(m, dukeImage));
        }
    }

    private void showResultToUser(CommandResult commandResult) {
        final Optional<List<Task>> taskList = commandResult.getTaskList();
        if (taskList.isPresent()) {
            showTaskList(taskList.get(), commandResult);
        } else {
            showToUser(commandResult.feedbackToUser);
        }
    }

    private void showTaskList(List<Task> taskList, CommandResult commandResult) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task task : taskList) {
            formattedTasks.add(task.toString());
        }
        showToUserAsIndexList(formattedTasks, commandResult);
    }

    private void showToUserAsIndexList(List<String> list, CommandResult commandResult) {
        showToUser(getIndexedList(list, commandResult));
    }

    private String getIndexedList(List<String> list, CommandResult commandResult) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : list) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        formatted.append("\n").append(commandResult.feedbackToUser);
        return formatted.toString();
    }

    private static String getIndexedListItem(int index, String item) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, index, item);
    }

    private void exitApplication() {
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

}

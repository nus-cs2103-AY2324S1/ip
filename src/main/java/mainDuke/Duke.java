package mainDuke;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainDuke.exceptions.DukeException;
import mainDuke.exceptions.TaskParseException;
import mainDuke.stageStuff.DialogBox;
import mainDuke.task.Task;


/**
 * Main program, responsible for running everything and connecting components together.
 */
public class Duke {
    /**
     * Types of task.
     */
    public enum TaskType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        EVENT,
        TODO,
        DEADLINE,
        FIND,
        TAG
    }
    protected Stage primaryStage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = "getResponse(userInput.getText())";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }


    public String getResponse(String input) {
        try {
            StringBuilder response;
            TaskList.updateFromStorage();
            response = new StringBuilder();
            TaskType taskType = Parser.parseType(input);
            switch (taskType) {
            case LIST: {
                response.append(TaskList.getListAsString());
                break;
            }

            case MARK: {
                int tasknum = Parser.getMarkIndex(input);
                response.append(TaskList.markTask(tasknum));
                break;
            }

            case UNMARK: {
                int tasknum = Parser.getUnmarkIndex(input);
                response.append(TaskList.unmarkTask(tasknum));
                break;
            }

            case DEADLINE:
            case TODO:
            case EVENT:
                Task nextTask = Parser.parseTask(input, taskType);
                response.append(TaskList.addTask(nextTask));
                break;

            case DELETE: {
                response.append(TaskList.deleteTask(Parser.getDeleteIndex(input)));
                break;
            }

            case FIND: {
                ArrayList<Task> list = TaskList.find(input);
                if (list.size() == 0) {
                    response.append("there are no matching tasks!");
                } else {
                    String header = "Here are the matching tasks in your list:\n";
                    String listInString = Ui.getListAsString(list);
                    response.append(header).append(listInString);
                }
                break;
            }

            case BYE: {
                response.append("Bye. Hope to see you again soon!");
                break;
            }

            default: {
                throw new DukeException("I can't identify your command!");
            }
            }
            TaskList.saveList();
            return response.toString();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (TaskParseException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
    }
}

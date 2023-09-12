package emiya.controllers;

import emiya.Emiya;
import emiya.Keyword;
import emiya.commands.DeadlineCommand;
import emiya.commands.DeleteCommand;
import emiya.commands.EventCommand;
import emiya.commands.FindCommand;
import emiya.commands.MarkCommand;
import emiya.commands.TodoCommand;
import emiya.commands.UnmarkCommand;
import emiya.emiyaexception.EmiyaException;
import emiya.emiyaexception.UnknownCommandException;
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

    private Emiya emiya;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEmiya(Emiya e) {
        emiya = e;
    }

    /**
     * Instantiates an Emiya instance, then loads the contents of the specified file into the task list
     * of the instance.
     */
    public void createEmiyaInstance() {
        emiya = new Emiya("data", "emiya.txt");
        emiya.loadList();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Emiya's reply and then appends them to
     * the dialog container. Executes the command given by the user. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;

        try {
            String[] parsedInput = emiya.getParser().parseToRemoveUnknownCommands(input);
            String taskDetails = parsedInput[1];
            Keyword typeOfTask = Keyword.getCommand(parsedInput[0]);

            switch (typeOfTask) {
            case LIST:
                response = emiya.getTaskList().list();
                break;
            case MARK:
                response = MarkCommand.mark(taskDetails, emiya.getTaskList(), emiya.getStorage(),
                        emiya.getUi(), emiya.getFileName(), emiya.getDirName());
                break;
            case UNMARK:
                response = UnmarkCommand.unmark(taskDetails, emiya.getTaskList(), emiya.getStorage(),
                        emiya.getUi(), emiya.getFileName(), emiya.getDirName());
                break;
            case DELETE:
                response = DeleteCommand.delete(taskDetails, emiya.getTaskList(), emiya.getStorage(),
                        emiya.getUi(), emiya.getFileName(), emiya.getDirName());
                break;
            case TODO:
                response = TodoCommand.createTodo(taskDetails, emiya.getTaskList(), emiya.getStorage(),
                        emiya.getUi(), emiya.getFileName(), emiya.getDirName());
                break;
            case DEADLINE:
                response = DeadlineCommand.createDeadline(taskDetails, emiya.getParser(), emiya.getTaskList(),
                        emiya.getStorage(), emiya.getUi(), emiya.getFileName(), emiya.getDirName());;
                break;
            case EVENT:
                response = EventCommand.createEvent(taskDetails, emiya.getParser(), emiya.getTaskList(),
                        emiya.getStorage(), emiya.getUi(), emiya.getFileName(), emiya.getDirName());
                break;
            case FIND:
                response = FindCommand.find(taskDetails, emiya.getTaskList());
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (EmiyaException e) {
            response = e.getMessage();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEmiyaDialog(response, dukeImage)
        );
        userInput.clear();
    }
}


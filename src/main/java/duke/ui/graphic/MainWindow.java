package duke.ui.graphic;

import duke.Duke;
import duke.parse.DateTimeManager;
import duke.parse.Parser;
import duke.parse.command.Command;
import duke.task.Task;
import duke.ui.Ui;
import duke.ui.graphic.components.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    /**
     * Initialises the UI.
     * @param name the name of the bot to be displayed to user.
     * @param args system arguments
     */
    @Override
    public void initialise(String name, String[] args) {
        String data = "Hello from " + name + "\n"
                + "What can I do for you?";
        this.displayData(data);
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        try {
            Command command = Parser.parse(input);
            command.execute(this.duke);
        } catch (Parser.ParseError e) {
            this.notifyError(e.getMessage());
        }
    }

    /**
     * Notifies the user that data is being loaded.
     */
    public void notifyDataLoading() {}

    /**
     * Notifies the user that data has been loaded.
     */
    public void notifyDataLoaded() {}

    /**
     * Notifies the user that data could not be loaded due to IO error.
     */
    public void notifyLoadingIoError() {}

    /**
     * Notifies the user that data is corrupted and allow user to take action.
     */
    public boolean handleFileCorrupted() { return true; }

    /**
     * Takes input from the user.
     * @return the input from the user
     */
    public String takeInput(String prompt) { return "test"; }

    /**
     * Leave an exit message.
     */
    public void exit() {}

    /**
     * Notifies user-input error.
     */
    public void notifyError(String message) {
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, this.dukeImage)
        );
        this.userInput.clear();
    }

    /**
     * Notifies user that a task has been marked done.
     * @param task the task to notify
     */
    public void notifyMarkDone(Task task) {}

    /**
     * Notifies user that a task has been marked as not done.
     * @param task the task to notify
     */
    public void notifyMarkNotDone(Task task) {}

    /**
     * Notifies that a task has been removed.
     * @param task the task removed
     */
    public void notifyRemoved(Task task) {}

    /**
     * Notifies that a list of task is going to be displayed.
     * Does not display the tasks itself.
     * @param type type of task (todo/deadline/event/default)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date before which to display deadlines before or events happening on,
     *             null if not to filter by date
     */
    public void notifyList(Ui.Type type, boolean isExcludingDone, LocalDate date) {
        String typeString;
        switch (type) {
            case TODO:
                typeString = "to-do tasks";
                break;
            case DEADLINE:
                typeString = "deadlines";
                break;
            case EVENT:
                typeString = "events";
                break;
            default:
                typeString = "tasks";
                break;
        }
        this.displayData(
                "Alright, here is your list of "
                        + typeString
                        + (isExcludingDone ? " not done" : "")
                        + (date != null
                        ? (type == Type.DEADLINE
                        ? " before "
                        : type == Type.EVENT
                        ? " happening on "
                        : " for "
                ) + DateTimeManager.dateToDisplay(date)
                        : ""
                ) + ":"
        );
    }

    /**
     * Notifies that a task has been added.
     * @param task the task added
     */
    public void notifyAdded(Task task) {}

    /**
     * Notifies that data is being saved to disk.
     */
    public void notifyDataSaving() {}

    /**
     * Notifies the user that data has been saved to disk.
     */
    public void notifyDataSaved() {}

    /**
     * Show task count.
     * @param count the number of task in the list
     */
    public void showTaskCount(int count) {}

    /**
     * Notify the user of the search results.
     * @param input the search parameter
     */
    public void notifyFind(String input) {}

    /**
     * Display custom data
     */
    public void displayData(String data) {
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(data, this.dukeImage)
        );
        this.userInput.clear();
    };
}

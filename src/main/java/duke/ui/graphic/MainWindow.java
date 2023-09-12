package duke.ui.graphic;

import java.time.LocalDate;

import duke.Duke;
import duke.parse.DateTimeManager;
import duke.parse.Parser;
import duke.parse.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.graphic.components.DialogBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

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
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));
    private String errorPrepend;
    private String errorAppend;
    private boolean isHandlingFileCorrupted = false;
    private String botName;

    /**
     * Initialises the UI.
     * @param name The name of the bot to be displayed to user.
     * @param args System arguments.
     */
    @Override
    public void initialise(String name, String[] args) {
        this.botName = name;
        try {
            this.duke.readFromDisk();
            this.greet();
        } catch (Storage.FileCorruptedException e) {
            this.displayData("Quack, memory was found to be corrupted!\n"
                    + "What do you wish to do?\n"
                    + "1. Quit, let me restore the data manually\n"
                    + "2. Continue with an empty task list\n"
                    + "Please indicate your option (1/2)."
            );
            this.isHandlingFileCorrupted = true;
        }

        this.dialogContainer.heightProperty().addListener((observable) -> {
            this.scrollPane.setVvalue(1.0);
        });
        this.dialogContainer.maxWidthProperty().bind(this.scrollPane.widthProperty());
        this.dialogContainer.minWidthProperty().bind(this.scrollPane.widthProperty());
        this.scrollPane.getContent().setOnScroll(scrollEvent -> {
            this.scrollPane.setVvalue((this.scrollPane.getVvalue() * this.dialogContainer.getHeight()
                    - scrollEvent.getDeltaY())
                    / this.dialogContainer.getHeight());
        });
    }

    private void greet() {
        this.displayData("Hello from " + this.botName + "\n"
                + "What can I do for you?");
    }

    /**
     * Set the reference to the bot controlling this UI.
     * @param duke The bot to control this UI.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Set the prepending and appending string of all error messages to this UI.
     * @param errorPrepend The prepending string for all error messages.
     * @param errorAppend The appending string for all error messages.
     */
    public void setErrorPrependAndAppend(String errorPrepend, String errorAppend) {
        this.errorPrepend = errorPrepend;
        this.errorAppend = errorAppend;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (this.isHandlingFileCorrupted) {
            this.handleFileCorrupted();
            this.userInput.clear();
            return;
        }
        String input = this.userInput.getText();
        this.dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        this.userInput.clear();
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
    @Override
    public void notifyDataLoading() {
        this.displayData("Loading data from hard disk ...");
    }

    /**
     * Notifies the user that data has been loaded.
     */
    @Override
    public void notifyDataLoaded() {
        this.displayData("Done loading.");
    }

    /**
     * Notifies the user that data could not be loaded due to IO error.
     */
    @Override
    public void notifyLoadingIoError() {
        this.displayData("Quack, an error has occurred while trying to save data to hard disk.\n"
                + "Starting with an empty task list.");
    }

    /**
     * Notifies the user that data is corrupted and allow user to take action.
     * @return Whether the user has decided to exit the programme.
     */
    @Override
    public boolean handleFileCorrupted() {
        String input = this.userInput.getText();
        switch (input) {
        case "1":
            this.isHandlingFileCorrupted = false;
            this.exit();
            return true;
        case "2":
            this.isHandlingFileCorrupted = false;
            this.greet();
            return false;
        default:
            this.displayData("Quack, I do not understand your option, please indicate again (1/2)!");
            return false;
        }
    }

    /**
     * Takes input from the user.
     * @return The input from the user.
     */
    @Override
    public String takeInput(String prompt) {
        throw new UnsupportedOperationException();
    }

    /**
     * Exits the programme.
     */
    @Override
    public void exit() {
        Platform.exit();
    }

    /**
     * Notifies user-input error.
     */
    @Override
    public void notifyError(String message) {
        this.displayData(this.errorPrepend + message + this.errorAppend);
    }

    /**
     * Notifies user that a task has been marked done.
     * @param task The task to notify.
     */
    @Override
    public void notifyMarkDone(Task task) {
        this.displayData("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Notifies user that a task has been marked as not done.
     * @param task The task to notify.
     */
    @Override
    public void notifyMarkNotDone(Task task) {
        this.displayData("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Notifies that a task has been removed.
     * @param task The task removed.
     */
    @Override
    public void notifyRemoved(Task task) {
        this.displayData("Noted, I've removed this task:\n" + task);
    }

    /**
     * Notifies that a list of task is going to be displayed.
     * Displays the task list.
     * @param type Type of task (todo/deadline/event/default).
     * @param isExcludingDone Whether to exclude tasks already done.
     * @param date The date before which to display deadlines before or events happening on,
     *             null if not to filter by date.
     * @param taskList The task list to display.
     */
    @Override
    public void notifyList(Task.Type type, boolean isExcludingDone, LocalDate date, TaskList taskList) {
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
                        ? (type == Task.Type.DEADLINE
                        ? " before "
                        : type == Task.Type.EVENT
                        ? " happening on "
                        : " for "
                ) + DateTimeManager.dateToDisplay(date)
                        : ""
                ) + ":\n" + taskList.getTasks(isExcludingDone, date, type)
        );
    }

    /**
     * Notifies that a task has been added.
     * @param task The task added.
     */
    public void notifyAdded(Task task) {
        this.displayData("Got it, I've added this task to the list:\n" + task);
    }

    /**
     * Notifies that data is being saved to disk.
     */
    @Override
    public void notifyDataSaving() {
        this.displayData("Saving data ...");
    }

    /**
     * Notifies the user that data has been saved to disk.
     */
    @Override
    public void notifyDataSaved() {
        this.displayData("Done saving.");
    }

    /**
     * Shows task count.
     * @param count The number of task in the list.
     */
    @Override
    public void showTaskCount(int count) {
        this.displayData("Now you have " + count + " in the list.");
    }

    /**
     * Notify the user of the search results.
     * @param input The search parameter.
     */
    @Override
    public void notifyFind(String input, String output) {
        this.displayData(
                "Here are the tasks that match \"" + input + "\"\n"
                + output
        );
    }

    /**
     * Displays custom data.
     */
    @Override
    public void displayData(String data) {
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(data, this.dukeImage)
        );
    };
}

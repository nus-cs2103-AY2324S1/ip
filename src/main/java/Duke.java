import Exceptions.DukeException;
import java.util.Scanner;
public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    public static final String DATA_FILE_PATH = "data/duke.txt";

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.showDukeError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Loads the list of tasks from the data file.
     * @return The status of loading the list of tasks.
     */
    public String loadTasks() {
        try {
            this.taskList = new TaskList(this.storage.load());
            return "Tasks loaded successfully!";
        } catch (DukeException e) {
            this.taskList = new TaskList();
            return "Tasks failed to load" + this.ui.showDukeError(e);
        } catch (Exception e) {
            return this.ui.showException(e);
        }
    }

    /**
     * Shows the welcome message.
     * @return the welcome message.
     */
    public String showWelcome() {
        return this.ui.showWelcome();
    }

    /**
     * Returns Chewy's response to the user's input.
     * @param userInput The user's input.
     * @return Chewy's response.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput.equals("bye")) {
                return this.ui.displayFarewellMessage();
            } else if (userInput.equals("help")) {
                return this.ui.displayHelpMessage();
            } else if (userInput.equals("list")) {
                return this.taskList.listTasks();
            } else if (userInput.startsWith("mark")) {
                return this.taskList.markTaskAsDone(userInput)
                        + this.taskList.saveTask(this.storage.filepath);
            } else if (userInput.startsWith("unmark")) {
                return this.taskList.unmarkTaskAsDone(userInput)
                        + this.taskList.saveTask(this.storage.filepath);
            } else if (userInput.startsWith("delete")) {
                return this.taskList.deleteTask(userInput)
                        + this.taskList.saveTask(this.storage.filepath);
            } else if (userInput.startsWith("find")) {
                return this.taskList.findTasks(userInput);
            } else {
                return this.taskList.addTask(userInput)
                        + this.taskList.saveTask(this.storage.filepath);
            }
        } catch (DukeException e) {
            return this.ui.showDukeError(e);
        } catch (Exception e) {
            return this.ui.showException(e);
        }
    }
}
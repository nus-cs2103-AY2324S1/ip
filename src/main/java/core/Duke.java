package core;
import Duke.storage.Storage;
import Duke.parser.Parser;
import Duke.tasks.*;
import Duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TaskManager tm;

    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        UNKNOWN
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);  
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        tm = new TaskManager(tasks);
    }

    /**
     * The main method for the Duke application.
     * It initializes the task list and listens for user commands,
     * handling them appropriately based on their type.
     *
     * @param args Command line arguments.
     */
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String userCommand = ui.readUserCommand();
            Parser p = new Parser();
            CommandType commandType = p.getCommandType(userCommand);
            switch (commandType) {
                case LIST:
                    ui.showTasks(tasks);
                    break;
                case BYE:
                    ui.showGoodbye();
                    storage.updateData(tasks);
                    isRunning = false;
                    break;
                case DELETE:
                    ui.showMessage(tm.handleDelete(userCommand));
                    break;
                case MARK:
                    ui.showMessage(tm.handleMark(userCommand));
                    break;
                case UNMARK:
                    ui.showMessage(tm.handleUnmark(userCommand));
                    break;
                case EVENT:
                case DEADLINE:
                    case TODO:
                    ui.showMessage(tm.addTask(userCommand));
                    break;
                case UNKNOWN:
                    ui.showError("unknown command.");
                    break;    
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
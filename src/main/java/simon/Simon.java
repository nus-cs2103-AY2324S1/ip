package simon;

import java.util.Scanner;
import simon.command.Parser;
import simon.task.Task;

/**
 * The main class for the {@code Simon} application.
 * It handles the primary loop of the application and interacts with the user.
 */
public class Simon {

    /** List of tasks maintained by the {@code Simon} application. */
    private TaskList tasks;

    /** Storage handler for saving and loading tasks from a file. */
    private final Storage storage;

    /** UI handler for displaying messages and prompts to the user. */
    private final Ui ui;

    /** Horizontal line for UI formatting. */
    protected static final String SPACE = "____________________________________________________________";

    /** Horizontal line with newline prefix for UI formatting. */
    protected static final String NSPACE = "\n____________________________________________________________";

    /** Horizontal line with newline suffix for UI formatting. */
    protected static final String SPACEN = "____________________________________________________________\n";

    /**
     * Constructs a new {@code Simon} application instance.
     *
     * @param filePath Path to the file where tasks will be saved and loaded from.
     */
    public Simon(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SimonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts and runs the main loop of the {@code Simon} application.
     * Continues to prompt the user for commands until the 'bye' command is given.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        ui.showWelcome();

        while (true) {
            String inData = scan.nextLine();
            Parser.Command command = Parser.parseCommand(inData.split(" ")[0]);

            try {
                switch (command) {
                    case LIST:
                        ui.listTasks(tasks);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task newTask = Parser.parseAddTask(inData, command);
                        tasks.addTask(newTask);
                        storage.save(tasks.getAllTasks());
                        ui.showAddedTask(newTask, tasks.getTaskCount());
                        break;
                    case UNMARK:
                        Task unmarkedTask = tasks.markTask(inData, false);
                        storage.save(tasks.getAllTasks());
                        ui.showMarkedTask(false, unmarkedTask);
                        break;
                    case MARK:
                        Task markedTask = tasks.markTask(inData, true);
                        storage.save(tasks.getAllTasks());
                        ui.showMarkedTask(true, markedTask);
                        break;
                    case DELETE:
                        Task deletedTask = tasks.deleteTask(inData);
                        storage.save(tasks.getAllTasks());
                        ui.showDeletedTask(deletedTask, tasks.getTaskCount());
                        break;
                    case FIND:
                        TaskList matchedTasks = tasks.findTasks(inData);
                        ui.showMatchingTasks(matchedTasks);
                        break;
                    case BYE:
                        ui.showGoodbye();
                        return;
                    case UNKNOWN:
                    default:
                        ui.showUnknownCommand();
                }
            } catch (SimonException se) {
                ui.showError(se.getMessage());
            }
        }
    }

    /**
     * The main entry point for the {@code Simon} application.
     *
     * @param args Command line arguments. Not used.
     */
    public static void main(String[] args) {
        new Simon("data/simon.txt").run();
    }
}
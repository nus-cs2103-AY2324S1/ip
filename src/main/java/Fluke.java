import java.io.IOException;
import java.util.Scanner;

public class Fluke {
    private final static String SAVE_FILE_NAME = "fluke.txt";
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    }
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Fluke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FlukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        // initialise Fluke
        Fluke fluke = new Fluke(SAVE_FILE_NAME);
        fluke.run();
    }

    public void run() {
        // greet the user
        this.ui.greet();
        // initialise scanner to check for user input
        Scanner scanner = new Scanner(System.in);
        boolean waitingForInput = true;
        while (waitingForInput) {
            // check for user commands
            try {
                String nextCommand = scanner.nextLine();
                Command commandType = Parser.parseCommand(nextCommand);
                switch (commandType) {
                case BYE:
                    waitingForInput = false;
                    this.ui.sayBye();
                    break;
                case LIST:
                    this.ui.showListOfTasks(tasks);
                    break;
                case MARK:
                case UNMARK:
                case DELETE:
                case TODO:
                case DEADLINE:
                case EVENT:
                    changeTodoList(commandType, nextCommand);
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (FlukeException d) {
                this.ui.showError(d.getMessage());
            }
        }
    }

    public Fluke() {
        // initialise ui
        this.ui = new Ui();
        // initialise task list
        this.tasks = new TaskList();
    };

    public void addTodo(String command) throws FlukeException {
        String parsedDescription = Parser.parseTodoCommand(command);
        Task taskAdded = this.tasks.addTodo(parsedDescription);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void addDeadline(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseDeadlineCommand(command);
        String description = parsedCommand[0];
        String by = parsedCommand[1];
        Task taskAdded = this.tasks.addDeadline(description, by);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void addEvent(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseEventCommand(command);
        String description = parsedCommand[0];
        String from = parsedCommand[1];
        String to = parsedCommand[2];
        Task taskAdded = this.tasks.addEvent(description, from, to);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void markTaskAsDone(String nextCommand) throws FlukeException {
        int index = Parser.parseMarkAsDoneCommand(nextCommand);
        Task taskMarked = tasks.markTaskAsDone(index);
        this.ui.showTaskMarkedAsDone(taskMarked);
    }

    private void markTaskAsUndone(String nextCommand) throws FlukeException {
        int index = Parser.parseMarkAsUndoneCommand(nextCommand);
        Task taskMarked = tasks.markTaskAsUndone(index);
        this.ui.showTaskMarkedAsUndone(taskMarked);
    }

    private void deleteTask(String nextCommand) throws FlukeException {
        int index = Parser.parseDeleteCommand(nextCommand);
        Task deleted = tasks.deleteTask(index);
        this.ui.showTaskDeleted(deleted, tasks);
    }

    /**
     * Helper function for additional logic related to changing the list.
     * @param commandType type of command
     * @param nextCommand the content in the command
     * @throws FlukeException an exception related to operations with Fluke
     * @throws IOException an exception related to saving data
     */
    private void changeTodoList(Command commandType, String nextCommand) throws FlukeException {
        // 1. make changes to the list
        switch (commandType) {
        case MARK:
            markTaskAsDone(nextCommand);
            break;
        case UNMARK:
            markTaskAsUndone(nextCommand);
            break;
        case DELETE:
            deleteTask(nextCommand);
            break;
        case TODO:
            addTodo(nextCommand);
            break;
        case DEADLINE:
            addDeadline(nextCommand);
            break;
        case EVENT:
            addEvent(nextCommand);
            break;
        default:
            // should not occur
            throw new FlukeException("An unknown error has occurred.");
        }
        // 2. save to file
        try {
            this.storage.save(tasks.getListOfTasks());
        } catch (IOException i) {
            throw new FlukeException(i.getMessage());
        }
    }
}

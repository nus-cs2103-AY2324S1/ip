package duke;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Main class for the Duke application.
 * <p>
 * The Duke application is a task management application that allows users to manage and track
 * tasks such as todos, deadlines, and events. It provides a command-line interface for users
 * to interact with.
 * </p>
 */
public class Duke {
    /** Task list to store and manage all tasks. */
    private TaskList taskList;

    /** User interface object for interacting with the user. */
    private final Ui ui;

    /** Storage object for saving and loading tasks to/from a file. */
    private final Storage storage;

    private boolean isRunning;

    /**
     * Initializes a new instance of the {@code Duke} class.
     * This constructor creates a new {@code Storage} instance to manage saving and loading tasks.
     * It also initializes the {@code Ui} for user interaction and sets the {@code isRunning} state to true.
     * Additionally, it loads any saved tasks from the storage into the {@code TaskList}.
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.isRunning = true;
        this.taskList = new TaskList(this.storage.loadTasks());
    }

    private void run() {
        System.out.println(this.ui.displayGreeting());
        while (this.isRunning) {
            String input = this.ui.readInput().strip();
            System.out.println(this.getResponse(input));
        }
    }
    /**
     * Exits the Duke application and performs necessary cleanup operations.
     * <p>
     * Before exiting, this method ensures the application is currently running using an assertion.
     * It then closes the user input interface, saves any current tasks to storage,
     * and sets the {@code isRunning} state to false.
     * </p>
     *
     * @throws DukeException If any error occurs during the exit operations,
     *                       such as issues with saving tasks to storage.
     */
    private void exit() throws DukeException {
        assert this.isRunning;
        this.ui.closeInput();
        this.storage.saveTasks(this.taskList.getTasks());
        this.isRunning = false;
    }

    /**
     * Processes the user's input command and returns the appropriate response.
     * This method takes in a user's command string, interprets the command type using
     * the {@code Parser} class, and then performs the respective operations based on the command type.
     * Some of the operations include adding tasks, marking tasks as done, deleting tasks, and more.
     * Any tasks-related modifications are saved to the storage.
     * In case of an unrecognized command or error in the command format, a {@code DukeException} is thrown
     * and the error message is returned to the user.
     *
     * @param input The user's input command string.
     * @return A string containing the response after processing the user's command.
     */
    public String getResponse(String input) {
        try {
            switch (Parser.parseCommand(input)) {
            case BYE:
                this.exit();
                return this.ui.displayExit();
            case LIST:
                return this.ui.displayList(taskList.getTasks());
            case TODO:
                ToDo todo = new ToDo(input.substring(5));
                this.taskList.add(todo);
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayTaskAdded(todo, taskList.getSize());
            case DEADLINE:
                String[] parts = input.substring(9).split(" /by ");
                if (parts.length < 2) {
                    throw new DukeException("duke.task.Deadline format is incorrect.");
                }
                Deadline deadline = new Deadline(parts[0], parts[1]);
                this.taskList.add(deadline);
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayTaskAdded(deadline, taskList.getSize());
            case EVENT:
                String[] eventParts = input.substring(6).split(" /from ");
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new DukeException("duke.task.Event format is incorrect.");
                }
                Event event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                this.taskList.add(event);
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayTaskAdded(event, taskList.getSize());
            case MARK:
                int taskNumberMark = Integer.parseInt(input.split(" ")[1]);
                this.taskList.get(taskNumberMark - 1).markAsDone();
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayMarkedAsDone(taskList.get(taskNumberMark - 1));
            case UNMARK:
                int taskNumberUnmark = Integer.parseInt(input.split(" ")[1]);
                this.taskList.get(taskNumberUnmark - 1).unmark();
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayMarkedAsNotDone(taskList.get(taskNumberUnmark - 1));
            case DELETE:
                int taskNumberDelete = Integer.parseInt(input.split(" ")[1]);
                Task removedTask = taskList.remove(taskNumberDelete - 1);
                this.storage.saveTasks(taskList.getTasks());
                return this.ui.displayTaskDeleted(removedTask, taskList.getSize());
            case TASKS_ON_DATE:
                LocalDate givenDate = Parser.getLocalDate(input);
                List<Task> tasksOnGivenDate = taskList.getTasks().stream()
                        .filter(task -> (task instanceof Deadline && ((Deadline) task).getBy()
                                .toLocalDate().isEqual(givenDate)) || (task
                                instanceof Event && Parser.isWithinEventDate((Event) task, givenDate)))
                        .collect(Collectors.toList());
                return this.ui.displayTasksOnDate(tasksOnGivenDate, givenDate);
            case FIND:
                String keyword = input.substring(5);
                TaskList resultList = taskList.findTasks(keyword);
                return this.ui.displayFindResults(resultList.getTasks());
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return this.ui.displayException(e);
        }
    }

    /**
     * The entry point for the Duke application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

package chadbod;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The ChadBod class represents a task management application.
 */
@SuppressWarnings("checkstyle:Regexp")
public class ChadBod {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final int TASKLIST_DISPLAY_OFFSET = 1;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // JavaFX components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a ChadBod instance using a default (pre-set) filepath "./data/tasks.txt".
     */
    public ChadBod() {
        this(FILE_PATH);
    }

    /**
     * Constructs a ChadBod instance using the given filepath.
     *
     * @param filePath the file path for storing task data.
     */
    public ChadBod(String filePath) {
        assert !filePath.isEmpty() : "No file path given.";
        assert filePath.equals(FILE_PATH) : "Erroneous file path given.";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (ChadBodException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the given user input, runs it if it is a valid command and generates a response string.
     *
     * @param input The user input to parse.
     * @return A response string generated based on the user input.
     */
    public String getResponse(String input) {
        String outcome;
        try {
            ParsedCommand parsedCommand = Parser.parseCommand(input);
            String details = parsedCommand.getDetails();
            switch (parsedCommand.getCommand()) {
            case BYE:
                outcome = handleBye();
                break;
            case LIST:
                outcome = handleList();
                break;
            case MARK:
                outcome = updateStatus(true, details);
                break;
            case UNMARK:
                outcome = updateStatus(false, details);
                break;
            case TODO:
                outcome = createTodo(details);
                break;
            case DEADLINE:
                outcome = createDeadline(details);
                break;
            case EVENT:
                outcome = createEvent(details);
                break;
            case DELETE:
                outcome = deleteTask(details);
                break;
            case FIND:
                outcome = findTask(details);
                break;
            default:
                throw new InvalidInputException();
            }
        } catch (NumberFormatException e) {
            outcome = ui.displayErrorMessage("☹ OOPS!!! Invalid task index.");
        } catch (ChadBodException e) {
            outcome = ui.displayErrorMessage(e.getMessage());
        }
        return outcome;
    }

    private int getTaskNumber(String details) throws NumberFormatException, TaskIndexOutOfBoundsException {
        int taskNumber = Integer.parseInt(details);
        boolean isValidTaskNumber = (taskNumber > TASKLIST_DISPLAY_OFFSET) && (taskNumber < tasks.getTaskCount());
        if (!isValidTaskNumber) {
            throw new TaskIndexOutOfBoundsException();
        }
        return taskNumber - TASKLIST_DISPLAY_OFFSET;
    }
    private String handleBye() {
        return ui.displayFarewell();
    }
    private String handleList() {
        return ui.displayTasks(tasks);
    }
    private String updateStatus(boolean markDone, String details) throws ChadBodException {
        int taskNumber = getTaskNumber(details);
        Task task = tasks.getTask(taskNumber);
        if (markDone) {
            task.markDone();
        } else {
            task.markUndone();
        }
        storage.saveTasks(tasks);
        return ui.displayStatusUpdate(markDone, task);
    }
    private String createTodo(String details) throws ChadBodException {
        if (details.isEmpty()) {
            throw new InvalidTaskException("Description of todo cannot be empty.");
        }
        Todo newTodo = new Todo(details);
        tasks.addTask(newTodo);
        storage.saveTasks(tasks);
        return ui.displayTaskAddedMessage(newTodo, tasks.getTaskCount());
    }
    private String createDeadline(String details) throws ChadBodException {
        if (details.isEmpty()) {
            throw new InvalidTaskException("Description of deadline cannot be empty.");
        }
        String[] deadlineDetails = details.split(" /by ", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
            throw new InvalidTaskException("Deadline due date cannot be empty.");
        }
        LocalDateTime byDate;
        try {
            byDate = LocalDateTime.parse(deadlineDetails[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Deadline due date/time not in ISO format (e.g. 2007-12-03T10:15:30).");
        }
        Deadline newDeadline = new Deadline(deadlineDetails[0], byDate);
        tasks.addTask(newDeadline);
        storage.saveTasks(tasks);
        return ui.displayTaskAddedMessage(newDeadline, tasks.getTaskCount());
    }

    /**
     * Creates a new Event task with the provided task details, adds it to the task list and
     * saves the updated task list to storage.
     * @param details The details of the event task, including its description, start and end timings.
     * @return A message indicating the event task has been successfully added to the list.
     * @throws ChadBodException if the provided details are invalid or updated task list cannot be saved.
     */
    protected String createEvent(String details) throws ChadBodException {
        if (details.isEmpty()) {
            throw new InvalidTaskException("Description of event cannot be empty.");
        }
        String[] eventDetails = details.split(" /from ", 2);
        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
            throw new InvalidTaskException("Event timings cannot be empty.");
        }
        String[] eventTimings = eventDetails[1].split(" /to ", 2);
        if (eventTimings.length < 2 || eventTimings[1].isEmpty()) {
            throw new InvalidTaskException("Event from and to timings cannot be empty.");
        }
        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(eventTimings[0]);
            toDate = LocalDateTime.parse(eventTimings[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Deadline due date/time not in ISO format (e.g. 2007-12-03T10:15:30).");
        }
        Event newEvent = new Event(eventDetails[0], fromDate, toDate);
        tasks.addTask(newEvent);
        storage.saveTasks(tasks);
        return ui.displayTaskAddedMessage(newEvent, tasks.getTaskCount());
    }
    private String deleteTask(String details) throws ChadBodException {
        int taskNumber = getTaskNumber(details);
        Task removedTask = tasks.removeTask(taskNumber);
        storage.saveTasks(tasks);
        return ui.displayTaskRemovedMessage(removedTask, tasks.getTaskCount());
    }
    private String findTask(String details) {
        TaskList matchingTasks = tasks.findTasksByKeyword(details);
        return ui.displayTasks(matchingTasks);
    }
}

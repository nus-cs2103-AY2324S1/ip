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
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (ChadBodException e) {
            ui.displayErrorMessage(e.getMessage());
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
                outcome = ui.displayFarewell();
                break;
            case LIST:
                outcome = ui.displayTasks(tasks);
                break;
            case MARK:
                int markTaskNumber = getTaskNumber(details);
                Task markedTask = tasks.getTask(markTaskNumber);
                storage.saveTasks(tasks);
                markedTask.markDone();
                outcome = ui.displayStatusUpdate(true, markedTask);
                break;
            case UNMARK:
                int unmarkTaskNumber = getTaskNumber(details);
                Task unmarkedTask = tasks.getTask(unmarkTaskNumber);
                unmarkedTask.markUndone();
                storage.saveTasks(tasks);
                outcome = ui.displayStatusUpdate(false, unmarkedTask);
                break;
            case TODO:
                if (details.isEmpty()) {
                    throw new InvalidTaskException("Description of todo cannot be empty.");
                }
                Todo newTodo = new Todo(details);
                tasks.addTask(newTodo);
                storage.saveTasks(tasks);
                outcome = ui.displayTaskAddedMessage(newTodo, tasks.getTaskCount());
                break;
            case DEADLINE:
                Deadline newDeadline = createDeadline(details);
                tasks.addTask(newDeadline);
                storage.saveTasks(tasks);
                outcome = ui.displayTaskAddedMessage(newDeadline, tasks.getTaskCount());
                break;
            case EVENT:
                Event newEvent = createEvent(details);
                tasks.addTask(newEvent);
                storage.saveTasks(tasks);
                outcome = ui.displayTaskAddedMessage(newEvent, tasks.getTaskCount());
                break;
            case DELETE:
                int taskNumber = getTaskNumber(details);
                Task removedTask = tasks.removeTask(taskNumber);
                storage.saveTasks(tasks);
                outcome = ui.displayTaskRemovedMessage(removedTask, tasks.getTaskCount());
                break;
            case FIND:
                TaskList matchingTasks = tasks.findTasksByKeyword(details);
                outcome = ui.displayTasks(matchingTasks);
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
        int unmarkTaskNumber = Integer.parseInt(details);
        if (unmarkTaskNumber < ChadBod.TASKLIST_DISPLAY_OFFSET
                || unmarkTaskNumber > tasks.getTaskCount() - 1 + TASKLIST_DISPLAY_OFFSET) {
            throw new TaskIndexOutOfBoundsException();
        }
        return unmarkTaskNumber - TASKLIST_DISPLAY_OFFSET;
    }

    private static Deadline createDeadline(String details) throws InvalidTaskException {
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
        return new Deadline(deadlineDetails[0], byDate);
    }

    /**
     * Creates an Event task from the given details.
     *
     * @param details the input details containing the event information.
     * @return the created Event task.
     * @throws InvalidTaskException if the details are invalid.
     */
    public static Event createEvent(String details) throws InvalidTaskException {
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
        return new Event(eventDetails[0], fromDate, toDate);
    }
}

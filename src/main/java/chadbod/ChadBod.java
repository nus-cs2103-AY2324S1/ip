package chadbod;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * The ChadBod class represents a task management application.
 */
public class ChadBod {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final int TASK_DISPLAY_OFFSET = 1;

    /**
     * Constructs a ChadBod instance.
     *
     * @param filePath The file path for storing task data.
     */
    public ChadBod(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (ChadBodException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the ChadBod application.
     */
    public void run() {
        ui.showGreeting();
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = sc.nextLine();
            try {
                ParsedCommand parsedCommand = Parser.parseCommand(input);
                String details = parsedCommand.getDetails();
                switch (parsedCommand.getCommand()) {
                    case BYE:
                        ui.showFarewell();
                        shouldExit = true;
                        break;
                    case LIST:
                        ui.printTasks(tasks);
                        break;
                    case MARK:
                        int markTaskNumber = getTaskNumber(details);
                        Task markedTask = tasks.getTask(markTaskNumber);
                        markedTask.markDone();
                        ui.printStatusUpdate(true, markedTask);
                        storage.saveTasks(tasks);
                        break;
                    case UNMARK:
                        int unmarkTaskNumber = getTaskNumber(details);
                        Task unmarkedTask = tasks.getTask(unmarkTaskNumber);
                        unmarkedTask.markUndone();
                        ui.printStatusUpdate(false, unmarkedTask);
                        storage.saveTasks(tasks);
                        break;
                    case TODO:
                        if (details.isEmpty()) {
                            throw new InvalidTaskException("Description of todo cannot be empty.");
                        }
                        Todo newTodo = new Todo(details);
                        tasks.addTask(newTodo);
                        ui.printTaskAddedMessage(newTodo, tasks.getTaskCount());
                        storage.saveTasks(tasks);
                        break;
                    case DEADLINE:
                        Deadline newDeadline = createDeadline(details);
                        tasks.addTask(newDeadline);
                        ui.printTaskAddedMessage(newDeadline, tasks.getTaskCount());
                        storage.saveTasks(tasks);
                        break;
                    case EVENT:
                        Event newEvent = createEvent(details);
                        tasks.addTask(newEvent);
                        ui.printTaskAddedMessage(newEvent, tasks.getTaskCount());
                        storage.saveTasks(tasks);
                        break;
                    case DELETE:
                        int taskNumber = getTaskNumber(details);
                        Task removedTask = tasks.removeTask(taskNumber);
                        ui.printTaskRemovedMessage(removedTask, tasks.getTaskCount());
                        storage.saveTasks(tasks);
                        break;
                    default:
                        throw new InvalidInputException();
                }
            } catch (NumberFormatException e) {
                ui.printErrorMessage("☹ OOPS!!! Invalid task index.");
            } catch (ChadBodException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Retrieves the task number from the input details.
     *
     * @param details The input details containing the task number.
     * @return The task number.
     * @throws NumberFormatException      If the task number cannot be parsed as an integer.
     * @throws TaskIndexOutOfBoundsException If the task number is out of bounds.
     */
    private int getTaskNumber(String details) throws NumberFormatException, TaskIndexOutOfBoundsException {
        int unmarkTaskNumber = Integer.parseInt(details);
        if (unmarkTaskNumber < ChadBod.TASK_DISPLAY_OFFSET ||
                unmarkTaskNumber > tasks.getTaskCount() - 1 + TASK_DISPLAY_OFFSET) {
            throw new TaskIndexOutOfBoundsException();
        }
        return unmarkTaskNumber - TASK_DISPLAY_OFFSET;
    }

    /**
     * Creates a Deadline task from the given details.
     *
     * @param details The input details containing the deadline information.
     * @return The created Deadline task.
     * @throws InvalidTaskException If the details are invalid.
     */
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
            throw new InvalidTaskException("Deadline due date/time not in ISO format. (e.g. 2007-12-03T10:15:30)");
        }
        return new Deadline(deadlineDetails[0], byDate);
    }

    /**
     * Creates an Event task from the given details.
     *
     * @param details The input details containing the event information.
     * @return The created Event task.
     * @throws InvalidTaskException If the details are invalid.
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
        LocalDateTime fromDate, toDate;
        try {
            fromDate = LocalDateTime.parse(eventTimings[0]);
            toDate = LocalDateTime.parse(eventTimings[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Deadline due date/time not in ISO format. (e.g. 2007-12-03T10:15:30)");
        }
        return new Event(eventDetails[0], fromDate, toDate);
    }

    /**
     * The entry point for the ChadBod application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new ChadBod(FILE_PATH).run();
    }
}

package chad.chatengine;

import chad.io.IOHandler;
import chad.io.ConsoleIO;
import chad.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Core engine for handling chat interactions and tasks.
 */
public class ChatEngine {
    final IOHandler ioHandler;
    TaskList taskList; // stores list of tasks
    final Storage storage;

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Constructs a new ChatEngine.
     * @param filePath the path where tasks are stored.
     */
    public ChatEngine(String filePath) {
        this.ioHandler = new ConsoleIO();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTasks()); // load tasks from storage
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Constructs a new ChatEngine for testing.
     * @param ioHandler mock io handler for testing.
     * @param taskList mock task list for testing.
     * @param filePath mock file path for testing.
     */
    ChatEngine(IOHandler ioHandler, TaskList taskList, String filePath) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
        this.storage = new Storage(filePath);
    }

    /**
     * Starts the chat interaction.
     */
    public void start() {
        ioHandler.greet();
        boolean canContinueChat = true;
        while(canContinueChat) {
            String input = ioHandler.readInput();;
            try {
                String[] parsedInput = Parser.parseInput(input);
                canContinueChat = commandHandler(parsedInput);
            } catch (ChadException e) {
                ioHandler.writeOutput("Error: " + e.getMessage());
            }
        }
        ioHandler.sayGoodbye();
    }

    /**
     * Handles various commands parsed from the input.
     * @param parsedInput the parsed input array.
     * @return true if chat should continue, false otherwise.
     * @throws ChadException if an invalid command or argument is encountered.
     */
    boolean commandHandler(String[] parsedInput) throws ChadException {
        Parser.CommandType command = Parser.parseCommandType(parsedInput[0]);
        switch (command) {
            case MARK:
                handleMark(parsedInput);
                break;
            case UNMARK:
                handleUnmark(parsedInput);
                break;
            case LIST:
                handleList();
                break;
            case TODO:
                handleTodo(parsedInput);
                break;
            case DEADLINE:
                handleDeadline(parsedInput);
                break;
            case EVENT:
                handleEvent(parsedInput);
                break;
            case DELETE:
                handleDelete(parsedInput);
                break;
            case BYE:
                return false;
            default:
                throw new ChadException.InvalidCommandException("Unknown command: " + parsedInput[0]);
        }
        return true;
    }

    /**
     * Marks a task as done.
     * @param parsedInput the parsed input array.
     * @throws ChadException if an invalid index is provided.
     */
    void handleMark(String[] parsedInput) throws ChadException {
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String response = taskList.markTaskAsDone(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    /**
     * Marks a task as not done.
     * @param parsedInput the parsed input array.
     * @throws ChadException if an invalid index is provided.
     */
    void handleUnmark(String[] parsedInput) throws ChadException {
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String response = taskList.markTaskAsNotDone(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    /**
     * Lists all the tasks.
     */
    void handleList() {
        ioHandler.writeOutput(taskList.displayTasks());
    }

    /**
     * Adds a new ToDo task.
     * @param parts the parsed input array containing the task description.
     */
    void handleTodo(String[] parts) {
        taskList.addTodo(parts[1]);
        ioHandler.writeOutput("Added new ToDo: " + parts[1]);
        saveTasks();
    }

    /**
     * Adds a new Deadline task.
     * @param parts the parsed input array containing the task and deadline details.
     * @throws ChadException if the date format is incorrect.
     */
    void handleDeadline(String[] parts) throws ChadException {
        String[] deadlineParts = parts[1].split(" /by ", 2);
        try {
            LocalDateTime dueDate = LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            taskList.addDeadline(deadlineParts[0], dueDate);
            ioHandler.writeOutput("Added new Deadline: " + deadlineParts[0] + " by " + dueDate);
            saveTasks();
        } catch (DateTimeParseException e) {
            throw new ChadException.InvalidFormatException("Invalid date format. Please use " + DATE_FORMAT);
        }
    }

    /**
     * Adds a new Event task.
     * @param parts the parsed input array containing the event and time details.
     * @throws ChadException if the date format is incorrect.
     */
    void handleEvent(String[] parts) throws ChadException {
        try {
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            LocalDateTime start = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDateTime end = LocalDateTime.parse(eventParts[2], DateTimeFormatter.ofPattern(DATE_FORMAT));
            taskList.addEvent(eventParts[0], start, end);
            ioHandler.writeOutput("Added new Event: " + eventParts[0] + " from " + start + " to " + end);
            saveTasks();
        } catch (DateTimeParseException e) {
            throw new ChadException.InvalidFormatException("Invalid date format. Please use " + DATE_FORMAT);
        }
    }

    /**
     * Deletes a task.
     * @param parts the parsed input array containing the index of the task to be deleted.
     */
    void handleDelete(String[] parts) {
        int index = Integer.parseInt(parts[1]) - 1;
        String response = taskList.deleteTask(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    /**
     * Saves the current state of tasks to the storage.
     */
    void saveTasks() {
        try {
            storage.saveTasks(taskList);
        } catch (FileNotFoundException e) {
            ioHandler.writeOutput("Error: File not found. " + e.getMessage());
        } catch (NoSuchFileException e) {
            ioHandler.writeOutput("Error: Directory not found. " + e.getMessage());
        } catch (IOException e) {
            ioHandler.writeOutput("Error saving tasks: " + e.getMessage());
        }
    }

}

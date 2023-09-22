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

    static final String EXIT_COMMAND = "EXIT";

    /**
     * Constructs a new ChatEngine.
     * 
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
     * 
     * @param ioHandler mock io handler for testing.
     * @param taskList  mock task list for testing.
     * @param filePath  mock file path for testing.
     */
    ChatEngine(IOHandler ioHandler, TaskList taskList, String filePath) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
        this.storage = new Storage(filePath);
    }

    /**
     * Handles various commands parsed from the input.
     * 
     * @param parsedInput the parsed input array.
     * @return String response from execution of the command.
     * @throws ChadException if an invalid command or argument is encountered.
     */
    String commandHandler(String[] parsedInput) throws ChadException {
        Parser.CommandType command = Parser.parseCommandType(parsedInput[0]);
        switch (command) {
        case MARK:
            return handleMark(parsedInput);
        case UNMARK:
            return handleUnmark(parsedInput);
        case LIST:
            return handleList();
        case TODO:
            return handleTodo(parsedInput);
        case DEADLINE:
            return handleDeadline(parsedInput);
        case EVENT:
            return handleEvent(parsedInput);
        case DELETE:
            return handleDelete(parsedInput);
        case FIND:
            return handleFind(parsedInput);
        case BYE:
            return EXIT_COMMAND;
        default:
            throw new ChadException.InvalidCommandException("Unknown command: " + parsedInput[0]);
        }
    }

    /**
     * Marks a task as done.
     * 
     * @param parsedInput the parsed input array.
     */
    String handleMark(String[] parsedInput) {
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String responseToUser = taskList.markTaskAsDone(index);
        saveTasks();
        return responseToUser;
    }

    /**
     * Marks a task as not done.
     * 
     * @param parsedInput the parsed input array.
     */
    String handleUnmark(String[] parsedInput){
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String responseToUser = taskList.markTaskAsNotDone(index);
        saveTasks();
        return responseToUser;
    }

    /**
     * Lists all the tasks.
     */
    String handleList() {
        return taskList.displayTasks();
    }

    /**
     * Adds a new ToDo task.
     * 
     * @param parts the parsed input array containing the task description.
     */
    String handleTodo(String[] parts) {
        taskList.addTodo(parts[1]);
        String responseToUser = "Added new ToDo: " + parts[1];
        saveTasks();
        return responseToUser;
    }

    /**
     * Adds a new Deadline task.
     * 
     * @param parts the parsed input array containing the task and deadline details.
     * @throws ChadException if the date format is incorrect.
     */
    String handleDeadline(String[] parts) throws ChadException {
        String[] deadlineParts = parts[1].split(" /by ", 2);
        try {
            LocalDateTime dueDate = LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            taskList.addDeadline(deadlineParts[0], dueDate);
            String responseToUser = "Added new Deadline: " + deadlineParts[0] + " by " + dueDate;
            saveTasks();
            return responseToUser;
        } catch (DateTimeParseException e) {
            throw new ChadException.InvalidFormatException("Invalid date format. Please use " + DATE_FORMAT);
        }
    }

    /**
     * Adds a new Event task.
     * 
     * @param parts the parsed input array containing the event and time details.
     * @throws ChadException if the date format is incorrect.
     */
    String handleEvent(String[] parts) throws ChadException {
        try {
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            LocalDateTime start = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDateTime end = LocalDateTime.parse(eventParts[2], DateTimeFormatter.ofPattern(DATE_FORMAT));
            taskList.addEvent(eventParts[0], start, end);
            String responseToUser = "Added new Event: " + eventParts[0] + " from " + start + " to " + end;
            saveTasks();
            return responseToUser;
        } catch (DateTimeParseException e) {
            throw new ChadException.InvalidFormatException("Invalid date format. Please use " + DATE_FORMAT);
        }
    }

    /**
     * Deletes a task.
     * 
     * @param parts the parsed input array containing the index of the task to be
     *              deleted.
     */
    String handleDelete(String[] parts) {
        int index = Integer.parseInt(parts[1]) - 1;
        String responseToUser = taskList.deleteTask(index);
        saveTasks();
        return responseToUser;
    }

    String handleFind(String[] parsedInput) {
        String keyword = parsedInput[1];
        return taskList.findTasks(keyword);
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String[] parsedInput = Parser.parseInput(input);
            return commandHandler(parsedInput);
        } catch (ChadException e) {
            return "Error: " + e.getMessage();
        }
    }
}

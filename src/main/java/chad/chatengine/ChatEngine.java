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

public class ChatEngine {
    final IOHandler ioHandler;
    TaskList taskList; // stores list of tasks
    final Storage storage;

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public ChatEngine(String filePath) {
        this.ioHandler = new ConsoleIO();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTasks()); // load tasks from storage
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    // New constructor for testing
    ChatEngine(IOHandler ioHandler, TaskList taskList, String filePath) {
        this.ioHandler = ioHandler;
        this.taskList = taskList;
        this.storage = new Storage(filePath);
    }

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

    void handleMark(String[] parsedInput) throws ChadException {
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String response = taskList.markTaskAsDone(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    void handleUnmark(String[] parsedInput) throws ChadException {
        int index = Integer.parseInt(parsedInput[1]) - 1;
        String response = taskList.markTaskAsNotDone(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    void handleList() {
        ioHandler.writeOutput(taskList.displayTasks());
    }

    void handleTodo(String[] parts) {
        taskList.addTodo(parts[1]);
        ioHandler.writeOutput("Added new ToDo: " + parts[1]);
        saveTasks();
    }

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

    void handleDelete(String[] parts) {
        int index = Integer.parseInt(parts[1]) - 1;
        String response = taskList.deleteTask(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }
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

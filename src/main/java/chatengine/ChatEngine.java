package chatengine;

import io.IOHandler;
import io.ConsoleIO;
import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class ChatEngine {
    private final IOHandler ioHandler;
    private TaskList taskList; // stores list of tasks
    private final Storage storage   ;

    public enum CommandType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public ChatEngine(String filePath) {
        this.ioHandler = new ConsoleIO();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadTasks()); // load tasks from storage
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    public void start() {
        ioHandler.greet();
        boolean canContinueChat = true;
        while(canContinueChat) {
            String input = ioHandler.readInput();;
            try {
                canContinueChat = commandHandler(input);
            } catch (ChadException e) {
                ioHandler.writeOutput("Error: " + e.getMessage());
            }
        }
        ioHandler.sayGoodbye();
    }

    private CommandType parseCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    private boolean commandHandler(String input) throws ChadException {
        if (input.trim().isEmpty()) {
            throw new ChadException.InvalidArgumentException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        CommandType command = parseCommandType(parts[0]);

        try {
            switch (command) {
                case MARK:
                case UNMARK:
                    handleMarkUnmark(parts, command);
                    break;
                case LIST:
                    ioHandler.writeOutput(taskList.displayTasks());
                    break;
                case TODO:
                    handleTodo(parts);
                    break;
                case DEADLINE:
                    handleDeadline(parts);
                    break;
                case EVENT:
                    handleEvent(parts);
                    break;
                case DELETE:
                    handleDelete(parts);
                    break;
                case BYE:
                    return false;
                case UNKNOWN:
                default:
                    throw new ChadException.InvalidCommandException("Unknown command: " + parts[0]);
            }
        } catch (NumberFormatException e) {
            throw new ChadException.InvalidFormatException("Invalid number format.");
        }
        return true;
    }

    private void handleMarkUnmark(String[] parts, CommandType command) throws ChadException {
        if (parts.length < 2) {
            throw new ChadException.InvalidArgumentException("Missing index for " + command);
        }
        int index = Integer.parseInt(parts[1]) - 1;
        String response = (command == CommandType.MARK) ? taskList.markTaskAsDone(index) : taskList.markTaskAsNotDone(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }

    private void handleTodo(String[] parts) throws ChadException {
        if (parts.length < 2) {
            throw new ChadException.InvalidArgumentException("Missing task description for ToDo.");
        }
        taskList.addTodo(parts[1]);
        ioHandler.writeOutput("Added new ToDo: " + parts[1]);
        saveTasks();
    }

    private void handleDeadline(String[] parts) throws ChadException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new ChadException.InvalidArgumentException("Invalid format for Deadline. Use: deadline {task} /by {date}");
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        taskList.addDeadline(deadlineParts[0], deadlineParts[1]);
        ioHandler.writeOutput("Added new Deadline: " + deadlineParts[0] + " by " + deadlineParts[1]);
        saveTasks();
    }

    private void handleEvent(String[] parts) throws ChadException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new ChadException.InvalidArgumentException("Invalid format for Event. Use: event {task} /from {start} /to {end}");
        }
        String[] eventParts = parts[1].split(" /from | /to ", 3);
        taskList.addEvent(eventParts[0], eventParts[1], eventParts[2]);
        ioHandler.writeOutput("Added new Event: " + eventParts[0] + " from " + eventParts[1] + " to " + eventParts[2]);
        saveTasks();
    }

    private void handleDelete(String[] parts) throws ChadException {
        if (parts.length < 2) {
            throw new ChadException.InvalidArgumentException("Invalid format for Delete Task Operation. Use: delete {taskIndex}");
        }
        int index = Integer.parseInt(parts[1]) - 1;
        String response = taskList.deleteTask(index);
        ioHandler.writeOutput(response);
        saveTasks();
    }
    private void saveTasks() {
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

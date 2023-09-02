package duke;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The main class that contains and controls the other components.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor to create the other components.
     * @param filePath File to read from when beginning program.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Main loop that constantly requests input from user when necessary.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public String run(String input) {
        try {
            String commandType = Parser.parseCommandType(input);
            switch (Objects.requireNonNull(commandType)) {
            case "bye" -> System.out.println("not done yet");
            case "list" -> {
                return Ui.listTasks(tasks.getTasks());
            }
            case "delete" -> {
                Task taskDeleted = tasks.deleteTask(Parser.taskNumber(input));
                Storage.writeToDisk(tasks.getTasks());
                return Ui.deleteTask(taskDeleted) + Ui.listTasks(tasks.getTasks());
            }
            case "mark" -> {
                Task taskMarked = tasks.markTask(Parser.taskNumber(input));
                Storage.writeToDisk(tasks.getTasks());
                return Ui.markTask(taskMarked) + Ui.listTasks(tasks.getTasks());
            }
            case "task" -> {
                tasks.addTask(Parser.newTask(input));
                Storage.writeToDisk(tasks.getTasks());
                return Ui.listTasks(tasks.getTasks());
            }
            case "find" -> {
                return Ui.foundTasks(tasks.findTasks(Parser.findKeyword(input)));
            }
            case "invalid" -> {
                return Ui.invalidCommand();
            }
            default -> throw new DukeException("Invalid task, uncaught by duke.Parser.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
    }
}

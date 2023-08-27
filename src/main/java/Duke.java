import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
    private boolean isRunning = true;
    private final TaskRepository taskRepository;
    private final ConsoleRenderer consoleRenderer;

    public Duke() {
        this.taskRepository = new TaskRepository();
        this.consoleRenderer = new ConsoleRenderer();
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        program.start();
    }

    private void start() {
        // Attempt to load data from data/tasks.txt
        try {
            taskRepository.loadSaveData();
        } catch (IOException exception) {
            consoleRenderer.printMessage(exception.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        // Welcome message
        consoleRenderer.printMessage("Hello! I'm Skye, your personal task assistant.\n"
                + "What can I do for you?");

        // Program only exits when user enters "bye" command
        do {
            try {
                String userInput = scanner.nextLine();
                System.out.println();
                String[] tokens = userInput.split(" ", 2);
                String command = !userInput.isEmpty() ? tokens[0] : "";

                // Switch statement to check for command keywords in the first word
                switch (command) {
                case "bye":
                    scanner.close();
                    exit();
                    break;

                case "list":
                    taskRepository.listTasks();
                    break;

                case "mark":
                    int completedTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                    taskRepository.markTask(completedTaskNumber);
                    break;

                case "unmark":
                    int incompleteTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                    taskRepository.unmarkTask(incompleteTaskNumber);
                    break;

                case "deadline":
                    addDeadline(tokens);
                    break;

                case "event":
                    addEvent(tokens);
                    break;

                case "todo":
                    addToDo(tokens);
                    break;

                case "delete":
                    int taskNumberToDelete = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                    taskRepository.deleteTask(taskNumberToDelete);
                    break;

                default:
                    throw new DukeException(DukeExceptionType.UNKNOWN_COMMAND);
                }
            } catch (DukeException | IOException exception) {
                consoleRenderer.printMessage(exception.getMessage());
            } catch (NumberFormatException exception) {
                consoleRenderer.printMessage("Error: Task number must be an integer.\n(example: mark 1)");
            }
        } while (isRunning);
    }

    private void exit() {
        consoleRenderer.printMessage("Bye. Hope to see you again soon!");
        isRunning = false;
        System.exit(0);
    }

    private String[] getEventParams(String[] tokens) throws DukeException {
        String commands = tokens[1];

        // Ensure that "/from" comes before "/to"
        if (
            !commands.contains("/from") || !commands.contains("/to")
            || commands.indexOf("/from") > commands.indexOf("/to")
        ) {
            throw new DukeException(DukeExceptionType.INVALID_EVENT_FORMAT);
        }

        // Split by /from or /to
        return commands.split("\\s+/\\w+");
    }

    private void addDeadline(String[] tokens) throws DukeException, IOException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.DEADLINE_NO_DESCRIPTION);
        }

        // Ensure that "/by" exists in the command
        if (!tokens[1].contains("/by")) {
            throw new DukeException(DukeExceptionType.INVALID_DEADLINE_FORMAT);
        }

        // Split by "by"
        String[] deadlineParams = tokens[1].split("/by");

        // Deadline attributes
        String deadlineDescription = deadlineParams[0].trim();
        String by = deadlineParams[1].trim();

        // Add deadline to tasks
        taskRepository.addTask(new Deadline(deadlineDescription, by));
    }

    private void addEvent(String[] tokens) throws DukeException, IOException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.EVENT_NO_DESCRIPTION);
        }

        String[] eventParams = getEventParams(tokens);

        // Event attributes
        String eventDescription = eventParams[0].trim();
        String from = eventParams[1].trim();
        String to = eventParams[2].trim();

        // Add event to tasks
        taskRepository.addTask(new Event(eventDescription, from, to));
    }

    private void addToDo(String[] tokens) throws DukeException, IOException {
        if (tokens.length < 2) {
            throw new DukeException(DukeExceptionType.TODO_NO_DESCRIPTION);
        } else {
            taskRepository.addTask(new ToDo(tokens[1]));
        }
    }
}
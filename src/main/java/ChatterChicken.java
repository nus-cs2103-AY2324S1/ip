import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatterChicken {

    public static final String LINE = "\n    _____________________________________________________________________________\n";

    public static final String INDENT = "      ";
    public static final String INDENT_BIG = "        ";

    public static final String PATH = "src/main/data/task-list.txt";

    private TaskList tasks;

    private Parser parser;
    private Storage storage;

    public ChatterChicken() {
        this.parser = new Parser();
        this.storage = new Storage(parser);
    }

    public static void main(String[] args) {
        ChatterChicken chatterChicken = new ChatterChicken();
        chatterChicken.run();
    }

    /**
     * Displays a greeting message to the user to introduce ChatterChicken.
     */
    private void greet() {
        System.out.println(LINE
                + INDENT + "Hello! I'm ChatterChicken!\n"
                + INDENT + "What can I do for you?"
                + LINE);
    }

    /**
     * Displays a farewell message to the user as they exit the ChatterChicken application.
     */
    private void exit() {
        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!" + LINE);
    }


    /**
     * Initiates the main loop of the ChatterChicken application.
     * The method reads user input, processes commands, and provides responses until the user chooses to  exit.
     * Exceptions are caught and displayed.
     */
    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            tasks = new TaskList(storage.loadTasksFromFile());
            greet();
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                Command command = parser.parseInput(input);
                executeCommand(command);
                input = sc.nextLine();
            }
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
        exit();
    }

    private void executeCommand(Command command) throws CCException {
        String action = command.getAction();
        String taskDescription = command.getTaskDescription();
        String output = "";
        switch (action) {
            case "list":
                tasks.printList();
                break;
            case "mark":
                tasks.markTask(taskDescription);
                break;
            case "unmark":
                tasks.unmarkTask(taskDescription);
                break;
            case "delete":
                tasks.deleteTask(taskDescription);
                break;
            case "todo":
            case "deadline":
            case "event":
                tasks.addTask(parser.parseTask(action, taskDescription));
                break;
            default:
                throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }
}

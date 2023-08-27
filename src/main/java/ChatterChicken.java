import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatterChicken {
    public static final String LINE = "\n    _____________________________________________________________________________\n";
    public static final String INDENT = "      ";
    public static final String INDENT_BIG = "        ";

    private static List tasks;

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
     * Parses the input command and performs corresponding actions based on the provided input.
     * This method handles various commands such as listing tasks, marking/unmarking tasks, deleting tasks,
     * and adding new tasks.
     *
     * @param input The input command provided by the user.
     * @throws CCException If an error occurs during parsing or execution of the command.
     */
    private void parseInput(String input) throws CCException {
        int space = input.indexOf(' ');
        String action;
        if (space == -1) {
            action = input;
        } else {
            action = input.substring(0, input.indexOf(' '));
        }
        switch (action) {
        case "list":
            tasks.printList();
            break;
        case "mark":
            tasks.markTask(input);
            break;
        case "unmark":
            tasks.unmarkTask(input);
            break;
        case "delete":
            tasks.deleteTask(input);
            break;
        case "todo":
        case "deadline":
        case "event":
            tasks.addTask(action, input);
            break;
        default:
            throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }

    /**
     * Initiates the main loop of the ChatterChicken application.
     * The method reads user input, processes commands, and provides responses until the user chooses to  exit.
     * Exceptions are caught and displayed.
     */
    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            tasks = new List();
            greet();
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                parseInput(input);
                input = sc.nextLine();
            }
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
        exit();
    }

    public static void main(String[] args) {
        ChatterChicken chatterChicken = new ChatterChicken();
        chatterChicken.run();
    }
}

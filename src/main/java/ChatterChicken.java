import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatterChicken {
    public static final String LINE = "\n    _____________________________________________________________________________\n";
    public static final String INDENT = "      ";
    public static final String INDENT_BIG = "        ";

    private static List list;

    private void greet() {
        System.out.println(LINE
                + INDENT + "Hello! I'm ChatterChicken!\n"
                + INDENT + "What can I do for you?"
                + LINE);
    }

    private void exit() {
        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!" + LINE);
    }

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
                list.printList();
                break;
            case "mark":
                list.markTask(input);
                break;
            case "unmark":
                list.unmarkTask(input);
                break;
            case "delete":
                list.deleteTask(input);
                break;
            case "todo":
            case "deadline":
            case "event":
                list.addTask(action, input);
                break;
            default:
                throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }

    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            list = new List();
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
        ChatterChicken cc = new ChatterChicken();
        cc.run();
    }
}

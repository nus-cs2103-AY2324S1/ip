import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatterChicken {

    public static final String LINE = "\n    _____________________________________________________________________________\n      ";
    public static final String INDENT = "      ";
    public static final String INDENT_BIG = "        ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println(LINE + "Hello! I'm ChatterChicken!\n" + INDENT + "What can I do for you?" + LINE);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else {
                String action = input.substring(0, input.indexOf(' '));
                switch (action) {
                    case "mark":
                        list.markTask(input);
                        break;
                    case "unmark":
                        list.unmarkTask(input);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        list.addTask(action, input);
                        break;
                }
            }
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
        exit();
    }

    /**
     * Executes the specified command by invoking corresponding methods on the tasks.
     * @param command The parsed user command.
     * @throws CCException If an error occurs during command execution.
     */
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
        System.out.println(LINE + "Bye. Hope to see you again soon!" + LINE);
    }
}

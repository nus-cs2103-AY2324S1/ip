import extensions.EkudException;
import extensions.EkudInvalidCommandException;
import extensions.TaskList;
import java.util.Scanner;
public class Ekud {
    // Basic UI & messages for the chatbot to print to console
    private static final String HORIZONTALLINE = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    private static final String INTRO = "Hello there! I'm Ekud. :)\nWhat can I do for you? :O";
    private static final String OUTRO = "Goodbye, have a nice day! :p";
    // TaskList object to store all of user's tasks
    private static TaskList taskList = new TaskList();
    /**
     * Prints a message formatted in between 2 horizontal lines for the user.
     * @param message
     */
    public static void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                HORIZONTALLINE,
                message,
                HORIZONTALLINE));
    }
    /**
     * Core function for instructing the TaskList object to execute commands and handle
     * invalid inputs.
     * @param command Input command by the user.
     * @param userArgs Args for the command supplied by the user.
     * @throws EkudException Either invalid commands or illegal arguments for the commands.
     */
    public static void executeCommand(String command, String userArgs) throws EkudException {
        switch (command) {
            case "list":
                taskList.showTasks();
                break;
            case "mark":
                taskList.markTaskAsDone(userArgs);
                break;
            case "unmark":
                taskList.markTaskAsNotDone(userArgs);
                break;
            case "todo":
                taskList.addToDo(userArgs);
                break;
            case "deadline":
                taskList.addDeadline(userArgs);
                break;
            case "event":
                taskList.addEvent(userArgs);
                break;
            case "delete":
                taskList.deleteTask(userArgs);
                break;
            default:
                throw new EkudInvalidCommandException(
                        String.format("Command '%s' not found :(",
                                command));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ekud.echo(INTRO);
        // process user input
        String userInput = scanner.nextLine();
        int firstSpace = userInput.indexOf(' ');
        String command = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        // main chatbot functionality
        while (!command.equals("end")) {
            String userArgs = firstSpace == -1 ? "": userInput.substring(firstSpace + 1);
            try {
                Ekud.executeCommand(command, userArgs);
            } catch(EkudException e) {
                Ekud.echo(e.toString());
            }
            userInput = scanner.nextLine();
            firstSpace = userInput.indexOf(' ');
            command = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        }
        Ekud.echo(OUTRO);
        scanner.close();
    }
}

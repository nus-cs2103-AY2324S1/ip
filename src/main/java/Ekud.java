import extensions.EkudIllegalArgException;
import extensions.EkudException;
import extensions.EkudInvalidCommandException;
import extensions.TaskList;
import java.util.Scanner;
public class Ekud {
    // Basic UI text for the chatbot to print to console
    private static final String horizontalLine = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    private static final String intro = "Hello there! I'm Ekud. :)\nWhat can I do for you? :O";
    private static final String outro = "Goodbye, have a nice day! :p";
    // TaskList object to store all of user's tasks
    private static final TaskList taskList = new TaskList();

    /**
     * Prints an intro message for the user.
     */
    public static void intro() {
        echo(intro);
    }

    /**
     * Prints an outro message for the user.
     */
    public static void outro() {
        echo(outro);
    }

    /**
     * Prints a message formatted in between 2 horizontal lines for the user.
     * @param message
     */
    public static void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                horizontalLine,
                message,
                horizontalLine));
    }
    /**
     * Core function for executing user commands.
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
                try {
                    int index = Integer.valueOf(userArgs) - 1;
                    taskList.markTaskAsDone(index);
                    break;
                } catch(NumberFormatException e) {
                    throw new EkudIllegalArgException("Please input a valid index number :o");
                }
            case "unmark":
                try {
                    int index = Integer.valueOf(userArgs) - 1;
                    taskList.markTaskAsNotDone(index);
                    break;
                } catch(NumberFormatException e) {
                    throw new EkudIllegalArgException("Please input a valid index number :o");
                }
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
                try {
                    int index = Integer.valueOf(userArgs) - 1;
                    taskList.deleteTask(index);
                    break;
                } catch(NumberFormatException e) {
                    throw new EkudIllegalArgException("Please input a valid index number :o");
                }

            default:
                throw new EkudInvalidCommandException(
                        String.format("Command '%s' not found :(",
                                command));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ekud.intro();
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
        Ekud.outro();
        scanner.close();
    }
}

import extensions.EkudException;
import extensions.EkudIOException;
import extensions.EkudInvalidCommandException;
import extensions.TaskList;

import java.util.Scanner;

public class Ekud {
    // Basic UI & messages for the chatbot to print to console
    private static final String HORIZONTAL_LINE = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    private static final String INTRO = "Hello there! I'm Ekud. :)\n" +
            "What can I do for you? :O";
    private static final String OUTRO = "Goodbye, have a nice day! :p";
    // TaskList object to store and manipulate all of user's tasks, as well as
    // handle invalid inputs
    private static TaskList taskList = new TaskList();
    /**
     * Prints a message formatted in between 2 horizontal lines for the user.
     * @param message Text to be printed.
     */
    public static void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                HORIZONTAL_LINE,
                message,
                HORIZONTAL_LINE));
    }
    /**
     * Represents a fixed set of input command strings which can be assigned to the
     * Command variable.
     */
    public static enum Command {
        SHOWTASKS("list"),
        MARKTASKASDONE("mark"),
        MARKTASKASNOTDONE("unmark"),
        ADDTODO("todo"),
        ADDDEADLINE("deadline"),
        ADDEVENT("event"),
        DELETETASK("delete");
        private String command;
        private Command(String command) {
            this.command = command;
        }
        public static Command getCommand(String userInput) {
            for (Command command : Command.values()) {
                if (command.command.equals(userInput)) return command;
            }
            return null;
        }
    }
    /**
     * Core function for instructing the TaskList object to execute commands and handle
     * invalid inputs, for which EkudExceptions would be thrown by TaskList.
     * @param command Input command by the user.
     * @param userArgs Args for the command supplied by the user.
     * @throws EkudException Either invalid commands or illegal arguments for the commands.
     */
    public static void handleCommand(Command command, String userArgs) throws EkudException {
        if (command == null) {
            throw new EkudInvalidCommandException("Command not found :(");
        }
        switch (command) {
        case SHOWTASKS:
            taskList.showTasks();
            break;
        case MARKTASKASDONE:
            taskList.markTaskAsDone(userArgs);
            break;
        case MARKTASKASNOTDONE:
            taskList.markTaskAsNotDone(userArgs);
            break;
        case ADDTODO:
            taskList.addToDo(userArgs);
            break;
        case ADDDEADLINE:
            taskList.addDeadline(userArgs);
            break;
        case ADDEVENT:
            taskList.addEvent(userArgs);
            break;
        case DELETETASK:
            taskList.deleteTask(userArgs);
            break;
        default:
            throw new EkudInvalidCommandException("Command not found :(");
        }
    }
    // Main chatbot program
    public static void main(String[] args) {
        // Load up saved tasks
        try {
            taskList.loadData();
        } catch (EkudException e) {
            System.out.println(e);
        }
        Scanner scanner = new Scanner(System.in);
        Ekud.echo(INTRO);
        // Process user input
        String userInput = scanner.nextLine();
        int firstSpace = userInput.indexOf(' ');
        String inputCommand = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        // Main chatbot functionality
        while (!inputCommand.equals("end")) {
            Command command = Command.getCommand(inputCommand);
            String userArgs = firstSpace == -1 ? "": userInput.substring(firstSpace + 1);
            try {
                Ekud.handleCommand(command, userArgs); // throws EkudException for invalid inputs
            } catch(EkudException e) {
                Ekud.echo(e.toString()); // catch and print out EkudException message
            }
            // Process next line of user input
            userInput = scanner.nextLine();
            firstSpace = userInput.indexOf(' ');
            inputCommand = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        }
        // Updated saved tasks before exiting the program
        try {
            System.out.println("Saving tasks...");
            taskList.saveData();
        } catch (EkudIOException e) {
            System.out.println(e);
        }
        Ekud.echo(OUTRO);
        scanner.close();
    }
}

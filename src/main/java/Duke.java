import java.util.Scanner;

/**
 * This is the Main class for the Duke program
 * @author Selwyn
 */
public class Duke {
    /**
     * The name of the bot
     */
    private static final String NAME = "Duke Prince";

    /**
     * This is the TaskList object for the whole program
     */
    private static TaskList taskList = new TaskList();

    /**
     * This is the main method to run the program
     * @param args
     */
    public static void main(String[] args) {
        greet();
        listen();
    }

    /**
     * This is the method for the program to listen to the user input to decide what commands to execute
     */
    protected static void listen() {
        boolean exitProgram = false;
        // Creating scanner object to get user input
        Scanner scanner = new Scanner(System.in);
        String input, userCommand, args;

        // Getting user input and performing relevant actions
        while(!exitProgram) {
            try {
                input = scanner.nextLine();
                printHorizontalLine();

                String[] parsedCommand = input.split(" ", 2);
                userCommand = parsedCommand[0];
                args = parsedCommand.length > 1 ? parsedCommand[1] : "";

                switch (userCommand) {
                case "bye":
                    exitProgram = true;
                    exit();
                    break;
                case "list":
                    taskList.displayTaskList();
                    break;
                case "todo":
                    taskList.addTask(TaskType.TODO, args);
                    break;
                case "deadline":
                    taskList.addTask(TaskType.DEADLINE, args);
                    break;
                case "event":
                    taskList.addTask(TaskType.EVENT, args);
                    break;
                case "mark":
                    taskList.changeTaskDoneStatus(args, true);
                    break;
                case "unmark":
                    taskList.changeTaskDoneStatus(args, false);
                    break;
                case "delete":
                    taskList.deleteTask(args);
                    break;
                default:
                    throw new DukeException("I don't understand what you are saying!\n" +
                            "Available commands are list, todo, deadline, event, mark, unmark, delete, bye.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch(Exception e) {
                System.out.println("Something has gone wrong! Please try again!");
            } finally {
                printHorizontalLine();
            }
        }
    }

    /**
     * This method greets the user upon starting the program
     */
    protected static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * This method says bye when user exits the program
     */
    protected static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints a horizontal line in the console
     */
    protected static void printHorizontalLine() {
        int width = 50;
        for (int i  = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
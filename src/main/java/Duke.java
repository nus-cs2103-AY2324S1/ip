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
     * The boolean value for whether program should continue getting user input
     */
    private static boolean getInput = true;

    /**
     * This is the TaskList object for the whole program
     */
    private static TaskList taskList = new TaskList();

    /**
     * This is the main method to run the program
     * @param args
     */
    public static void main(String[] args) {
        printHorizontalLine();
        greet();
        printHorizontalLine();

        // Creating scanner object to get user input
        Scanner scanner = new Scanner(System.in);
        String command;


        // Getting user input and performing relevant actions
        while(getInput) {
            try {
                command = scanner.nextLine();
                printHorizontalLine();

                int taskNumber;
                String[] parsedCommand = command.split(" ", 2);

                switch (parsedCommand[0]) {
                    case "bye":
                        getInput = false;
                        bye();
                        break;
                    case "list":
                        taskList.displayTaskList();
                        break;
                    case "mark":
                        taskNumber = Integer.parseInt(parsedCommand[1]);
                        if (taskNumber <= 0) {
                            throw new DukeException("Number must be more 1 or more!");
                        }
                        taskList.markTaskDone(taskNumber);
                        break;
                    case "unmark":
                        taskNumber = Integer.parseInt(parsedCommand[1]);
                        if (taskNumber <= 0) {
                            throw new DukeException("Number must be more 1 or more!");
                        }
                        taskList.markTaskUndone(taskNumber);
                        break;
                    case "todo":
                        if (parsedCommand.length == 1) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        taskList.createTask(parsedCommand[1]);
                        break;
                    case "deadline":
                        if (parsedCommand.length == 1) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] detailsAndDeadline = parsedCommand[1].split("/by", 2);
                        taskList.createTask(detailsAndDeadline[0].trim(), detailsAndDeadline[1].trim());
                        break;
                    case "event":
                        if (parsedCommand.length == 1) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        String[] detailsAndStartEnd = parsedCommand[1].split("/from", 2);
                        String[] startAndEnd = detailsAndStartEnd[1].split("/to", 2);
                        taskList.createTask(detailsAndStartEnd[0].trim(), startAndEnd[0].trim(), startAndEnd[1].trim());
                        break;
                    default:
                        throw new DukeException("I don't understand what you are saying!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                printHorizontalLine();
            }
        }
    }

    /**
     * This method greets the user upon starting the program
     */
    private static void greet() {
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    /**
     * This method says bye when user exits the program
     */
    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints a horizontal line in the console
     */
    private static void printHorizontalLine() {
        int width = 50;
        for (int i  = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
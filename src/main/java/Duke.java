import java.util.Scanner;

/**
 * This is the Main class for the Duke program
 * @author Selwyn
 */

public class Duke {
    /**
     * The name of the chatbot
     */
    private static final String NAME = "Duke Prince";

    /**
     * The template for spaces within the program
     */
    private static final String SPACER = "----------------------------------------------------";

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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Creating scanner object to get user input
        Scanner scanner = new Scanner(System.in);
        String response;

        greet();

        // Getting user input and performing relevant actions
        while(getInput) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                getInput = false;
                break;
            }

            System.out.println(SPACER);
            if (response.equals("list")) {
                taskList.displayTasks();
            } else {
                taskList.addTask(response);
            }
            System.out.println(SPACER);
            System.out.println();
        }

        bye();
    }

    /**
     * This method greets the user upon starting the program
     */
    private static void greet() {
        System.out.println(SPACER);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(SPACER);
        System.out.println();
    }

    /**
     * This method says bye when user exits the program
     */
    private static void bye() {
        System.out.println(SPACER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPACER);
        System.out.println();
    }
}
import java.util.Scanner;

/**
 * Manages user interactions in the Duke class.
 */
public class UI {

    //The scanner used to scan user inputs
    private static Scanner scan = new Scanner(System.in);

    /**
     * Scans user inputs.
     * @return The string user input scanned by the scanner.
     */
    public static String scan() {
        return scan.nextLine();
    }

    /**
     * Sends a greeting message to the user.
     */
    public static void greet() {
        System.out.println("____________________________________________________________\n" +
                " Hellooooooooooo! I'm Lati!\n" +
                " What can I do for you? :3\n" +
                "____________________________________________________________");
    }

    /**
     * Bids the user farewell after using the Duke bot.
     */
    public static void bye() {
        System.out.println(" Byeeeeee. Hope to see you again soon~~\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a message to the user about their stored task.
     * @param task Name of task.
     * @param index Number of tasks stored.
     */
    public static void store(String task, int index) {
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a message about deleted tasks
     * @param task The deleted task.
     * @param index The number of tasks left.
     */
    public static void delete(String task, int index) {
        System.out.println("Guess you don't want to do that anymore: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a message about marked tasks.
     * @param task The marked task.
     */
    public static void mark(String task) {
        System.out.println("NICEEEEE. Good job on completing the task!\n" +
                task + "\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a message about un-marked tasks.
     * @param task The unmarked tasks.
     */
    public static void unMark(String task) {
        System.out.println("Ohhh... uhm, okay, task undone!\n" +
                task + "\n" +
                "____________________________________________________________");
    }

    /**
     * Displays all tasks in a given array of tasks
     * @param tasks Array of tasks
     */
    public static void display(Object[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            int curr = i + 1;
            System.out.println(curr + ". " + tasks[i].toString() +"\n");
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message thrown by an exception.
     * @param errorMessage Exception error message.
     */
    public static void errorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a message pertaining to input errors
     * @param type The type of input error.
     */
    public static void inputErrorMessage(String type) {
        switch (type) {
        case "todo":
            System.out.println("Whoops, wrong format! Type todo <task>\n" +
                    "____________________________________________________________");
            break;
        case "deadline":
            System.out.println("Whoops, wrong format! Type deadline <task> /by YYYY-MM-DD HH:MM\n" +
                    "____________________________________________________________");
            break;
        case "event":
            System.out.println("Whoops, wrong format! " +
                    "Type event <task> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\n" +
                    "____________________________________________________________");
            break;
        case "mark":
            System.out.println("Ehh? What do you want to mark? Type mark <index>\n" +
                    "____________________________________________________________");
            break;
        case "unmark":
            System.out.println("Ehh? What do you want to unmark? Type unmark <index>\n" +
                    "____________________________________________________________");
            break;
        case "delete":
            System.out.println("Ehh? What do you want to delete? Type remove <index>\n" +
                    "____________________________________________________________");
            break;
        case "other":
            System.out.println("I dunno what you're saying...\n" +
                    "____________________________________________________________");
            break;
        }
    }


}

package duke;

import java.util.Scanner;

/**
 * Manages user interactions in the Duke class.
 */
public class Ui {

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
    public static String greet() {
        return " Hellooooooooooo! I'm Lati!\n" +
                " What can I do for you? :3\n";
    }

    /**
     * Bids the user farewell after using the Duke bot.
     */
    public static String bye() {
        return " Byeeeeee. Hope to see you again soon~~\n";
    }

    /**
     * Sends a message to the user about their stored task.
     * @param task Name of task.
     * @param index Number of tasks stored.
     */
    public static String store(String task, int index) {
        return "Added! You want to: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!");
    }

    /**
     * Sends a message about deleted tasks
     * @param task The deleted task.
     * @param index The number of tasks left.
     */
    public static String delete(String task, int index) {
        return "Guess you don't want to do that anymore: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!");
    }

    /**
     * Sends a message about marked tasks.
     * @param task The marked task.
     */
    public static String mark(String task) {
        return "NICEEEEE. Good job on completing the task!\n" +
                task;
    }

    /**
     * Sends a message about un-marked tasks.
     * @param task The unmarked tasks.
     */
    public static String unMark(String task) {
        return "Ohhh... uhm, okay, task undone!\n" +
                task;
    }

    /**
     * Displays all tasks in a given array of tasks
     * @param tasks Array of tasks
     */
    public static String display(Task[] tasks) {
        String fullList = "";
        for (int i = 0; i < tasks.length; i++) {
            int curr = i + 1;
            fullList = fullList + curr + ". " + tasks[i].toString() +"\n";
        }

        return fullList;
    }

    /**
     * Prints a missage that prints all matching messages.
     */
    public static String showMatchesMessage() {
        return "Here are all matching tasks: \n";
    }

    /**
     * Prints a message if nothing is in the list.
     */
    public static String emptyListMessage() {
        return "Hmm, nothing here...\n";
    }

    /**
     * Prints an error message thrown by an exception.
     * @param errorMessage Exception error message.
     */
    public static String errorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints a message pertaining to input errors
     * @param type The type of input error.
     */
    public static String inputErrorMessage(String type) {
        String errorMessage = new String();
        switch (type) {
            case "todo":
                errorMessage = "Whoops, wrong format! Type todo <task>\n";
                break;
            case "deadline":
                errorMessage = "Whoops, wrong format! Type deadline <task> /by YYYY-MM-DD HH:MM\n";
                break;
            case "event":
                errorMessage = "Whoops, wrong format! " +
                        "Type event <task> /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\n";
                break;
            case "mark":
                errorMessage = "Ehh? What do you want to mark? Type mark <index>\n";
                break;
            case "unmark":
                errorMessage = "Ehh? What do you want to unmark? Type unmark <index>\n";
                break;
            case "delete":
                errorMessage = "Ehh? What do you want to delete? Type remove <index>\n";
                break;
            default:
                errorMessage = "I dunno what you're saying...\n";
                break;
        }

        return errorMessage;
    }

    /**
     * Returns a message if a given index is beyond the DukeList's size.
     * @param listSize The size of the DukeList
     * @return Error message.
     */
    public static String indexError(int listSize) {
        return "Ehh? I can't find the task. You have " + listSize +  (listSize != 1 ? " tasks" : " task");
    }


}


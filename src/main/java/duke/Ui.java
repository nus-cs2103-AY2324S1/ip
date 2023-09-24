package duke;

/**
 * Manages user interactions in the Duke class.
 */
public class Ui {

    /**
     * Sends a greeting message to the user.
     */
    public static String greet() {
        return " Hellooooooooooo! I'm Lati!\n"
                + " What can I do for you? :3\n";
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
        return "Added! You want to: " + task + "\n"
                + "Now you have " + index + (index > 1 ? " tasks!" : " task!");
    }

    /**
     * Sends a message about deleted tasks
     * @param task The deleted task.
     * @param index The number of tasks left.
     */
    public static String delete(String task, int index) {
        return "Guess you don't want to do that anymore: " + task + "\n"
                + "Now you have " + index + (index > 1 ? " tasks!" : " task!");
    }

    /**
     * Sends a message about marked tasks.
     * @param task The marked task.
     */
    public static String mark(String task) {
        return "NICEEEEE. Good job on completing the task!\n"
                + task;
    }

    /**
     * Sends a message about un-marked tasks.
     * @param task The unmarked tasks.
     */
    public static String unMark(String task) {
        return "Ohhh... uhm, okay, task undone!\n"
                + task;
    }

    /**
     * Displays all tasks in a given array of tasks
     * @param tasks Array of tasks
     */
    public static String display(Task[] tasks) {
        String fullList = "";
        for (int i = 0; i < tasks.length; i++) {
            int curr = i + 1;
            fullList = fullList + curr + ". " + tasks[i].toString() + "\n";
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
        switch (type) {
        case "todo":
            return "Whoops, wrong format! Type todo <task>\n";
        case "deadline":
            return "Whoops, wrong format! Type deadline <task> /by YYYY-MM-DD\n";
        case "event":
            return "Whoops, wrong format! "
                    + "Type event <task> /from YYYY-MM-DD /to YYYY-MM-DD\n";
        case "mark":
            return "Ehh? What do you want to mark? Type mark <index>!"
                    + "Make sure the index is valid!\n";
        case "unmark":
            return "Ehh? What do you want to unmark? Type unmark <index>!"
                    + "Make sure the index is valid!\n";
        case "delete":
            return "Ehh? What do you want to remove? Type delete <index>!"
                    + "Make sure the index is valid!\n";
        case "addtrivia":
            return "Ehhhh? Type addtrivia <question> /answer <answer>";
        case "edittrivia":
            return "Ehhh? Type edittrivia <question> /answer <answer>";
        default:
            return "I dunno what you're saying...\n";
        }
    }

    /**
     * Returns a message upon adding trivia.
     * @param message The trivia added
     * @param answer The answer to the trivia
     * @return Message confirming the addition of trivia
     */
    public static String addTrivia(String message, String answer) {
        return "Woooaahhh... I didn't know that!\n"
                + message + "?\n"
                + answer + "!";

    }

    /**
     * Returns a message upon editing trivia
     * @param message The trivia edited
     * @param answer The edited answer
     * @return Confirmation of the edited trivia answer.
     */
    public static String editTrivia(String message, String answer) {
        return "Ohhhh...\n"
                + message + "?\n"
                + "It's actually " + answer + "!";
    }

    /**
     * Returns a message upon deleting trivia
     * @param message The trivia to be deleted
     * @return Confirmation that the trivia message is deleted
     */
    public static String deleteTrivia(String message) {
        return message + "?\n"
               + "... Guess we don't need to know now, huh";
    }

}


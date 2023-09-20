package duke;

/**
 * Used to handle user interface output.
 *
 * @author Teo Kai Sheng
 */
public class Ui {
    static final String LINE = "______________________________________________";

    /**
     * Displays a greeting message to the user.
     */
    public static void greet() {
        System.out.println(LINE + "\nHello, I'm KS task manager :)\nWhat can I do for you?\n" + LINE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public static String bye() {
        String output = "Bye. Hope to see you again soon!";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a horizontal line.
     */
    public static void horizontalLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message.
     */
    public static String unknownCommandErrorMessage() {
        String output = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String showListErrorMessage() {
        String output = "Did you mean list?";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String taskNotFoundErrorMessage() {
        String output = "Task does not exist.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String markTaskErrorMessage() {
        String output = "Please enter a number e.g., mark 1";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String updateTaskErrorMessage() {
        String output = "Format: update taskNumber /(desc, by, from, to) value, e.g. update 1 /from 2023-12-30";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String wrongTaskTypeErrorMessage() {
        String output = "This task type does not have the required field.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String unmarkTaskErrorMessage() {
        String output = "Please enter a number e.g., unmark 1";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String deleteTaskErrorMessage() {
        String output = "Please enter a number e.g., delete 1";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String invalidDateErrorMessage() {
        String output = "Enter valid date yyyy-mm-dd";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String addEventErrorMessage() {
        String output = "Format: event description /from yyyy-mm-dd /to yyyy-mm-dd";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String addDeadlineErrorMessage() {
        String output = "Format: deadline description /by yyyy-mm-dd";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String addToDoErrorMessage() {
        String output = "OOPS!!! The description of a todo cannot be empty.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message when the user enters an invalid command.
     */
    public static String findTasksErrorMessage() {
        String output = "What do you want me to find?";
        System.out.println(output);
        return output;
    }
}

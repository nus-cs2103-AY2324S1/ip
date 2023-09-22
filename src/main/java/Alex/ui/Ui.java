package Alex.ui;

import java.util.Scanner;

import Alex.storage.UserInputStorage;


/**
 * A class that is used to deal with user interface related stuff. Its behaviours include printing greeting message,
 * bye message, alert for several other commands especially when the commands typed by the user is in a wrong format.
 */
public class Ui {
    private static Scanner userInputScanner;

    /**
     * A static method that is used to print greeting message to user.
     */
    public static String greet() {
        String greeting = "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n";

        UserInputStorage.loadUserDateFromFile();
        userInputScanner = new Scanner(System.in);
        return greeting;
    }

    /**
     * A static method that is used to print bye message to the user when the user key in command "bye" to terminate
     * the execution of the chat bot Alex.
     */
    public static String bye() {
        String bye = "Bye bye. Hope to see you again soon!\n";
        String isStored = UserInputStorage.storeToFile();
        return bye + isStored;
    }

    /**
     * A static method that is used to read user input from the standard input, right strip the user input and return
     * that string.
     *
     * @return user input string.
     */
    public static String readUserInput() {
        String userInput = userInputScanner.nextLine();
        String userInputStripped = userInput.stripTrailing();
        return userInputStripped;
    }

    /**
     * A static method that is used to print an alert message to user when the user key in the mark command in
     * a wrong format.
     */
    public static String getAlertForMark() {
        String message = "To mark certain task to be done, please use the following format:\n"
                + "   " + "mark (task number in non-negative integer)\n";

        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user key in unmark command in
     * a wrong format.
     */
    public static String getAlertForUnmark() {
        String message = "To unmark certain task to be undone, please use the following format:\n"
                + "   " + "unmark (task number in non-negative integer)\n";

        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user key in delete command
     * in a wrong format.
     */
    public static String getAlertForDelete() {
        String message = "To delete a task, please use the following format:\n"
                + "   " + "delete (task number in non-negative integer)\n";

        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add a todo task
     * in a wrong format.
     */
    public static String getAlertForTodo() {
        String message = "OOPS!!! Please enter a todo task in the following format:\n"
                + "   " + "todo (description)";

        return message;

    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add a deadline task
     * in a wrong format.
     */
    public static String getAlertForDeadline() {
        String message = "Please enter a deadline task in the format: "
                + "deadline (description) /by yyyy-MM-dd HHmm\n";

        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add an event task
     * in a wrong format.
     */
    public static String getAlertForEvent() {
        String message = "Please enter an event task in the format: "
                + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n";

        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to
     * view the task(s) of a specific date but key in the command in a wrong format.
     */
    public static String getAlertForDate() {
        String message = "OOPS!! To view the task(s) on a specific date,please key in the date "
                + "in the format yyyy-MM-dd only.\n"
                + "Please also ensure that your key in date is a valid date\n"
                + "Note that it is case sensitive\n";


        return message;
    }

    /**
     * A static method that is used to print an alert message to the user when the user
     * key in unknown command.
     */
    public static String getAlertForUnknown() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";

        return message;
    }

    public static String getAlertForFind() {
        String message = "OOPS!! To find task(s) with matching word, please use the following format:\n"
                + "    find (description)\n";

        return message;
    }

}

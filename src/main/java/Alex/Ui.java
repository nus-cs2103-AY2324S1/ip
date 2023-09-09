package Alex;

import java.util.Scanner;

/**
 * A class that is used to deal with user interface related stuff. Its behaviours include printing greeting message,
 * bye message, alert for several other commands especially when the commands typed by the user is in a wrong format.
 */
public class Ui {
    public static final String horizontalLine = "_____________________________________________________________\n";
    private static Scanner userInputScanner;

    /**
     * A static method that is used to print greeting message to user.
     */
    public static void greet() {
        String greeting = horizontalLine
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + horizontalLine;

        System.out.println(greeting);
        UserInputStorage.loadUserDateFromFile();
        userInputScanner = new Scanner(System.in);
    }

    /**
     * A static method that is used to print bye message to the user when the user key in command "bye" to terminate
     * the execution of the chat bot Alex.
     */
    public static void bye() {
        String bye = Ui.horizontalLine
                + "Bye bye. Hope to see you again soon!\n"
                + Ui.horizontalLine;
        System.out.println(bye);
        UserInputStorage.storeToFile();
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
    public static void printAlertForMark() {
        String message = "To mark certain task to be done, please use the following format:\n"
                + "   " + "mark (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user key in unmark command in
     * a wrong format.
     */
    public static void printAlertForUnmark() {
        String message = "To unmark certain task to be undone, please use the following format:\n"
                + "   " + "unmark (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user key in delete command
     * in a wrong format.
     */
    public static void printAlertForDelete() {
        String message = "To delete a task, please use the following format:\n"
                + "   " + "delete (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add a todo task
     * in a wrong format.
     */
    public static void printAlertForTodo() {
        String message = "OOPS!!! Please enter a todo task in the following format:\n"
                + "   " + "todo (description)";
        System.out.println(Ui.horizontalLine
                + message + "\n"
                + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add a deadline task
     * in a wrong format.
     */
    public static void printAlertForDeadline() {
        String message = "Please enter a deadline task in the format: " +
                "deadline (description) /by yyyy-MM-dd HHmm\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to add an event task
     * in a wrong format.
     */
    public static void printAlertForEvent() {
        String message = "Please enter an event task in the format: "
                + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n";
        System.out.println(
                Ui.horizontalLine
                        + message
                        + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user try to
     * view the task(s) of a specific date but key in the command in a wrong format.
     */
    public static void printAlertForDate() {
        String message = "OOPS!! To view the task(s) on a specific date,please key in the date " +
                "in the format yyyy-MM-dd only.\n"
                + "Please also ensure that your key in date is a valid date\n"
                + "Note that it is case sensitive\n";
        System.out.println(
                Ui.horizontalLine
                        + message
                        + Ui.horizontalLine
        );
    }

    /**
     * A static method that is used to print an alert message to the user when the user
     * key in unknown command.
     */
    public static void printAlertForUnknown() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

}

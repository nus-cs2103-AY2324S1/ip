package Alex;

import java.util.Scanner;

public class Ui {
    public static final String horizontalLine = "_____________________________________________________________\n";
    private static Scanner userInputScanner;
    public static void greet() {
        String greeting = horizontalLine
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + horizontalLine;

        System.out.println(greeting);
        UserInputStorage.loadUserDateFromFile();
        userInputScanner = new Scanner(System.in);
    }

    public static void bye() {
        String bye = Ui.horizontalLine
                + "Bye bye. Hope to see you again soon!\n"
                + Ui.horizontalLine;
        System.out.println(bye);
        UserInputStorage.storeToFile();
    }

    public static String readUserInput() {
        String userInput = userInputScanner.nextLine();
        String userInputStripped = userInput.stripTrailing();
        return userInputStripped;
    }

    public static void printAlertForMark() {
        String message = "To mark certain task to be done, please use the following format:\n"
                + "   " + "mark (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    public static void printAlertForUnmark() {
        String message = "To unmark certain task to be undone, please use the following format:\n"
                + "   " + "unmark (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    public static void printAlertForDelete() {
        String message = "To delete a task, please use the following format:\n"
                + "   " + "delete (task number in non-negative integer)\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    public static void printAlertForTodo() {
        String message = "OOPS!!! Please enter a todo task in the following format:\n"
                + "   " + "todo (description)";
        System.out.println(Ui.horizontalLine
                + message + "\n"
                + Ui.horizontalLine
        );
    }

    public static void printAlertForDeadline() {
        String message = "Please enter a deadline task in the format: " +
                "deadline (description) /by yyyy-MM-dd HHmm\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    public static void printAlertForEvent() {
        String message = "Please enter an event task in the format: "
                + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n";
        System.out.println(
                Ui.horizontalLine
                        + message
                        + Ui.horizontalLine
        );
    }

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

    public static void printAlertForUnknown() {
        String message = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        System.out.println(Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

    public static void printAlertForFind() {
        String message = "OOPS!! To find task(s) with matching word, please use the following format:\n"
                + "    find (description)\n";
        System.out.println(
                Ui.horizontalLine
                + message
                + Ui.horizontalLine
        );
    }

}

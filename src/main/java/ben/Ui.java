package ben;

import java.util.Scanner;

/**
 * Represents the UI handles user interactions with the GUI
 */
public class Ui {
    private Scanner user;

    public Ui() {
        this.user = new Scanner(System.in);
    }

    private final static String HORIZONTAL_LINE = "------------------------------------------";

    public String nextLine() {
        return user.nextLine();
    }

    public static String displayMessage(String message) {
        return message;
    }

    public static String greeting() {
        return displayMessage("What's up! I'm Ben\nWhat can I do for you?");
    }

    public static String bye() {
        return "Bye. For now";
    }

    public String displayList(TaskList tasks) {
        return displayMessage("\n" + tasks);
    }

    public static void showError(String message) {
        System.out.println(HORIZONTAL_LINE + "\n" + message + "\n" + HORIZONTAL_LINE);
    }

    public void showDateTimeParseError(String data) {
        System.out.println(HORIZONTAL_LINE + "\n" + data + " is not a valid command" + "\n" +
                "Please input the date in the following format: dd/mm/yyyy HHmm" + "\n" + HORIZONTAL_LINE);
    }
}

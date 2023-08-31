import java.util.Scanner;

public class Ui {
    private final Scanner user;

    public Ui() {
        this.user = new Scanner(System.in);
    }
    private final static String HORIZONTAL_LINE = "------------------------------------------";

    public String nextLine() {
        return user.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }
    public void greeting() {
        displayMessage("\nWhat's up! I'm Ben\nWhat can I do for you?\n");
    }

    public void bye() {
        displayMessage("\nBye. For now\n");
    }

    public void displayList(TaskList tasks) {
        displayMessage("\n" + tasks);
    }

    public static void showError(String message) {
        System.out.println(HORIZONTAL_LINE + "\n" + message + "\n" + HORIZONTAL_LINE);
    }

    public void showDateTimeParseError(String data) {
        System.out.println(HORIZONTAL_LINE + "\n" + data + " is not a valid command" + "\n" +
                "Please input the date in the following format: dd/mm/yyyy HHmm" + "\n" + HORIZONTAL_LINE);
    }
}

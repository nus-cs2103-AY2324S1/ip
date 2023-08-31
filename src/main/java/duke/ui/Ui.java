package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm duke.Duke\n What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void showGoodByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void showErrorMessage(String error) {
        showMessage("☹ OOPS!!! " + error);
    }

    public static String getUserInput() {
        return scanner.nextLine();
    }
}

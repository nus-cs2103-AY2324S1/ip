import java.util.Scanner;

public class Ui {
    public static String horizontalLine = "_".repeat(60) + "\n";
    public static void greet() {
        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
    }
    public static void sayBye() {
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }

    public static void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }
}

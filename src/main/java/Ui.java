import java.util.Scanner;

public class Ui {
    public static final String LINE_SEPARATOR = "____________________________________________________________";

    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(messageWithSeparator("Hello! I'm David\nWhat can I do for you?"));
    }

    public void showGoodbye() {
        System.out.println(messageWithSeparator("Bye. Hope to see you again soon!"));
    }

    public static String messageWithSeparator(String message) {
        return LINE_SEPARATOR+ "\n" + message + "\n" + LINE_SEPARATOR;
    }

    public static void printMessageWithSeparator(String message) {
        System.out.println(messageWithSeparator(message));
    }
}

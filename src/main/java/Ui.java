import java.util.Scanner;
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public Scanner getScanner() {
        return this.scanner;
    }

    public String helloMessage() {
        return "Hello! I'm Chatty\nWhat can I do for you?";
    }

    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String lineBreak() {
        return "____________________________________________________________";
    }

    public String getUserInput() {
        return this.scanner.nextLine();
    }

    public void closeScanner() {
        this.scanner.close();
    }
    public String markTaskDone() {
        return "Nice! I've marked this task as done:";
    }

    public String unmarkTaskDone() {
        return "OK, I've marked this task as not done yet:";
    }

    public String addTask() {
        return "Got it. I've added this task:";
    }

    public String removeTask() {
        return "Noted. I've removed this task:";
    }

    public String dateTimeParseExceptionMessage() {
        return "DateTime input not valid, please try again.";
    }
}

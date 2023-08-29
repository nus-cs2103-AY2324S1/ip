import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void echo(String line) {
        formatPrintMessage(line);
    }

    public void greet(String name) {
        System.out.println();
        formatPrintMessage("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println();
        formatPrintMessage("Bye. Hope to see you again soon!");
    }

    public void formatPrintMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}

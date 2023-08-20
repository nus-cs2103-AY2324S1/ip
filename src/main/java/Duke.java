import java.util.Scanner;

public class Duke {
    // Constructor for Duke
    public Duke() {

    }

    // Constants
    private static final String name = "Beep Boop";
    private static final String line = "─".repeat(100);

    // Fields
    private Scanner sc = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        System.out.println(line);
    }

    public void greet() {
        String greeting = String.format("Hello! I'm %s\n\tWhat can I do for you?\n", name);
        System.out.println(line);
        printMessage(greeting);
    }

    public void exit() {
        String exiting = "Bye. Hope to see you again soon! Beep Boop!\n";
        printMessage(exiting);
    }

    public void echo(String input) {
        printMessage(input);
    }

    public void runDuke() {
        greet();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                isDone = true;
            } else {
                echo(input);
            }
        }

        exit();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}

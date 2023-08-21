import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static void greet() {
        String greeting = "_________________________________________________\n"
                + " Hello! I'm Glub!\n"
                + " What can I do for you?\n"
                + "_________________________________________________\n";
        System.out.println(greeting);
    }

    static void echo(String input) {
        String echoMsg = "_________________________________________________\n"
                + String.format(" %s\n", input)
                + "_________________________________________________\n";
        System.out.println(echoMsg);
    }

    static void exit() {
        String exitMsg = "_________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_________________________________________________\n";
        System.out.println(exitMsg);
        System.exit(0);
    }
    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            if (Objects.equals(input, "bye")) {
                exit();
            } else {
                echo(input);
            }
        }
    }
}

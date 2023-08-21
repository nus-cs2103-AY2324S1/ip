import java.util.Scanner;
public class Duke {

    /**
     * Name of the bot.
     */
    private static final String NAME = "Cabbage";

    /**
     * Entry-point for the program. Displays a greeting, echos the user's input, and exits.
     * @param args CLI arguments passed into the program.
     */
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = scanner.nextLine();
        }
        exit();
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    /**
     * Echoes the user's input.
     * @param input The user's input.
     */
    public static void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

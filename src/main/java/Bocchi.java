import java.util.Scanner;


public class Bocchi {
    private static final String LINE_BREAK = "___________________________________________________";

    /**
     * Outputs greeting message.
     */
    private static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Bocchi");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs exit message.
     */
    private static void exit() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Echoes input message.
     */
    private static void echo(String message) {
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }

    /**
    /**
     * Main method.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            echo(command);
            command = sc.nextLine();
        }
        sc.close();
        exit();
    }
}

import java.util.Scanner;

public class Chatter {
    /**
     * Prints greeting message.
     */
    private static void greet() {
        System.out.println("-----------------------");
        System.out.println("Hello! I'm Chatter");
        System.out.println("What can I do for you?");
    }

    /**
     * Echoes the user's commands through a Scanner object, if user says bye,
     * print exit statement.
     */
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println("-----------------------");
            System.out.println(userInput);
            System.out.println("-----------------------");
            userInput = scanner.nextLine();
        }

        exit();

    }

    /**
     * Prints exit message.
     */
    private static void exit() {
        System.out.println("-----------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}

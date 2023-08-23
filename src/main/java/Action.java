import java.util.Scanner;

public class Action {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";


    /**
     * Greets the user by printing the greeting messages.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bell Curve God.");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Says goodbye to the user and exits.
     */
    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Echos commands entered by the user,
     * and exits when the user types the command bye.
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(input);
                System.out.println(HORIZONTAL_LINE);
            }
        }
        exit();
        sc.close();
    }
}

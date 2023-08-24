import java.util.Scanner;
public class Duke {
    private static final String DIVIDER = "      ____________________________________________________________";

    public static void main(String[] args) {
        // Opening lines
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);

        // Check user input
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // If user wants to quit
                System.out.println(DIVIDER);
                System.out.println("        Bye ~ Hope to see you again soon ~");
                System.out.println(DIVIDER);
                break;

            } else {
                // Other inputs
                System.out.println(DIVIDER);
                System.out.println("        Added: " + userInput);
                System.out.println(DIVIDER);
            }
        }
    }
}
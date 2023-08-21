import java.util.Scanner;

public class CarbonBot {
    private static String DIVIDER = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while(true) {
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) {
                // Stops reading user inputs if the command was 'bye'
                break;
            } else {
                // Otherwise, echos the command the user entered
                System.out.println(DIVIDER);
                System.out.println(cmd);
                System.out.println(DIVIDER);
            }
        }

        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}

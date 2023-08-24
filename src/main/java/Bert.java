import java.util.Scanner;

/**
 * A chatbot named Bert that introduces itself,
 * then echos commands entered by the user, until
 * the user types 'bye' in which the program exits
 */
public class Bert {
    /**
     * botName stores the name of the chatbot
     */
    private static final String botName = "Bert";

    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm " + Bert.botName + "\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n"
        );
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            System.out.println(
                    "____________________________________________________________\n" +
                    s + "\n" +
                    "____________________________________________________________\n"
            );
            s = sc.nextLine();
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }
}

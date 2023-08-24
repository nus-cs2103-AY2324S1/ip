import java.util.Scanner;
import java.util.ArrayList;

/**
 * A chatbot named Bert that introduces itself,
 * then reads and stores inputs entered by the user.
 * Typing 'list' produces a list of user inputs.
 * The program exits when the user types 'bye'.
 */
public class Bert {
    /**
     * botName stores the name of the chatbot
     */
    private static final String botName = "Bert";

    public static void main(String[] args) {
        // Introduces itself
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm " + Bert.botName + "\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n"
        );

        // Until the user inputs 'bye', any text the user input is read and stored.
        // Typing 'list' prints out the list of user inputs.
        // When the user inputs 'bye', the program exits.
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        ArrayList<String> al = new ArrayList<>();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < al.size(); i++) {
                    System.out.println(i + ". " + al.get(i) + "\n");
                }
                System.out.println("____________________________________________________________\n");
                s = sc.nextLine();
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                        "added: " + s + "\n" +
                        "____________________________________________________________\n"
                );
                al.add(s);
                s = sc.nextLine();
            }
        }

        // Exits
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }
}

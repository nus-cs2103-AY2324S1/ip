import java.util.Scanner;
import java.util.ArrayList;

/**
 * A chatbot named Bert that introduces itself,
 * then reads and stores tasks entered by the user.
 * Typing 'list' produces a list of tasks.
 * Typing 'mark x' or 'unmark x' marks or unmarks
 * the task at a specific index on the list.
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

        // Intialises a scanner to read user input
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        // Initialises an arraylist to store tasks
        ArrayList<Task> al = new ArrayList<>();

        // The program runs until the user enters 'bye'
        while (!s.equals("bye")) {
            String[] inputs = s.split(" ");

            // Typing 'list' prints out the list of tasks.
            if (inputs[0].equals("list")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Here are the tasks in your list:\n"
                );
                for (int i = 0; i < al.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + "." + al.get(i) + "\n");
                }
                System.out.println("____________________________________________________________\n");
                s = sc.nextLine().trim();

            // Typing 'mark x' or 'unmark x' marks or unmarks
            // the task at a specific index on the list.
            } else if (inputs[0].equals("mark")) {
                int i = Integer.parseInt(inputs[1]) - 1;
                Task t = al.get(i);
                t.markAsDone();
                al.set(i, t);
                System.out.println(
                        "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        "  " + t + "\n" +
                        "____________________________________________________________\n"
                        );
                s = sc.nextLine();
            } else if (inputs[0].equals("unmark")) {
                int i = Integer.parseInt(inputs[1]) - 1;
                Task t = al.get(i);
                t.markAsUndone();
                al.set(i, t);
                System.out.println(
                        "____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:\n" +
                        "  " + t + "\n" +
                        "____________________________________________________________\n"
                );
                s = sc.nextLine();

            // Any other text that the user inputs is read and stored
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                        "added: " + s + "\n" +
                        "____________________________________________________________\n"
                );
                Task t = new Task(s);
                al.add(t);
                s = sc.nextLine().trim();
            }
        }

        // Exit message is sent when the program exits
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }
}

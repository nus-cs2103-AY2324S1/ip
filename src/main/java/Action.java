import java.util.ArrayList;
import java.util.Scanner;

public class Action {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static ArrayList<String> tasks = new ArrayList<>();

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
    public static void respond() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
            }
        }
        exit();
        sc.close();
    }

    /**
     * Adds a task to the storage.
     * @param input the task to be added
     */
    public static void addTask(String input) {
        tasks.add(input);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + input);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * List all tasks stored.
     */
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }
}

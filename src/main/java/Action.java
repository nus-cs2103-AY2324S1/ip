import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that encapsulates all actions the Bell Curve God can do.
 */
public class Action {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * An ArrayList that stores all tasks entered by the user.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

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
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (words[0].equals("mark")) {
                mark(tasks.get(Integer.parseInt(words[1]) - 1));
            } else if (words[0].equals("unmark")) {
                unmark(tasks.get(Integer.parseInt(words[1]) - 1));
            } else {
                addTask(input);
            }
        }
        exit();
        sc.close();
    }

    /**
     * Adds a task to the storage.
     * @param input description of the task to be added
     */
    public static void addTask(String input) {
        tasks.add(new Task(input));
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + input);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * List all tasks stored.
     */
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Mark the given task as done.
     * @param task task to be marked as done.
     */
    public static void mark(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Mark the given task as not done.
     * @param task task to be marked as not done.
     */
    public static void unmark(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(HORIZONTAL_LINE);
    }
}

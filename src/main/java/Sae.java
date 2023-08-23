import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Sae class represents an interactive task manager.
 * Users can add, list, mark, and unmark tasks using this program.
 */
public class Sae {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");

        ArrayList<Task> store = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            String str = input.nextLine();
            String[] commandTask = str.split(" ", 2);

            String command = commandTask[0];

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                listTasks(store);
            } else if (command.equals("mark")) {
                markTask(store, commandTask);
            } else if (command.equals("unmark")) {
                unmarkTask(store, commandTask);
            } else {
                addTask(store, str);
            }
        }

        input.close();

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Lists all tasks in the store along with their statuses.
     *
     * @param store The ArrayList containing the tasks.
     */
    private static void listTasks(ArrayList<Task> store) {
        int len = store.size();
        for (int i = 0; i < len; i++) {
            Task curr = store.get(i);
            System.out.println((i + 1) + ".[" + curr.getStatusIcon() + "] " + curr.getDescription());
        }
    }

    /**
     * Marks a task as done based on the user's input.
     *
     * @param store        The ArrayList containing the tasks.
     * @param commandTask  The user's input split into command and content.
     */
    private static void markTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + curr.getStatusIcon() + "] " + curr.getDescription());
    }

    /**
     * Unmarks a task as done based on the user's input.
     *
     * @param store        The ArrayList containing the tasks.
     * @param commandTask  The user's input split into command and content.
     */
    private static void unmarkTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        curr.unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + curr.getStatusIcon() + "] " + curr.getDescription());
    }

    /**
     * Adds a new task to the storage.
     *
     * @param store The ArrayList containing the tasks.
     * @param str   The user's input representing the task.
     */
    private static void addTask(ArrayList<Task> store, String str) {
        Task curr = new Task(str);
        store.add(curr);
        System.out.println("added: " + str);
    }
}
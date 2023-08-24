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
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                executeCommand(store, commandTask);
            } catch (SaeException e) {
                System.out.println("☹ " + e.getMessage());
            }
        }

        input.close();
    }

    /**
     * Executes the command provided by the user and performs the corresponding action.
     *
     * @param store The ArrayList containing the tasks.
     * @param commandTask The user's input split into command and content.
     * @throws SaeException If an error specific to the Sae chatbot occurs.
     */
    private static void executeCommand(ArrayList<Task> store, String[] commandTask) throws SaeException {
        String command = commandTask[0];

        try {
            if (command.equals("delete")) {
                deleteTask(store, commandTask);
            } else if (command.equals("list")) {
                listTasks(store);
            } else if (command.equals("mark")) {
                markTask(store, commandTask);
            } else if (command.equals("unmark")) {
                unmarkTask(store, commandTask);
            } else if (command.equals("todo")) {
                if (commandTask.length < 2 || commandTask[1].isEmpty()) {
                    throw new InvalidTodoException();
                } else {
                    addTodoTask(store, commandTask);
                }
            } else if (command.equals("deadline")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/by")) {
                    throw new InvalidDeadlineException();
                }
                addDeadlineTask(store, commandTask);
            } else if (command.equals("event")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
                    throw new InvalidEventException();
                }
                addEventTask(store, commandTask);
            } else {
                throw new SaeException();
            }
        } catch (SaeException errorMessage) {
            System.out.println(errorMessage.toString());
        }
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
            System.out.println((i + 1) + "." + curr.toString());
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
        System.out.println(curr.toString());
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
        System.out.println(curr.toString());
    }

    /**
     * Adds a new Todo task to the store.
     *
     * @param store       The ArrayList containing the tasks.
     * @param commandTask The user's input split into command and description.
     */
    private static void addTodoTask(ArrayList<Task> store, String[] commandTask) {
        Task curr = new Todo(commandTask[1]);
        store.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Adds a new Deadline task to the store.
     *
     * @param store       The ArrayList containing the tasks.
     * @param commandTask The user's input split into command and description.
     */
    private static void addDeadlineTask(ArrayList<Task> store, String[] commandTask) {
        String[] parts = commandTask[1].split("/by");
        Task curr = new Deadline(parts[0].trim(), parts[1].trim());
        store.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Adds a new Event task to the store.
     *
     * @param store       The ArrayList containing the tasks.
     * @param commandTask The user's input split into command and description.
     */

    private static void addEventTask(ArrayList<Task> store, String[] commandTask) {
        String[] parts = commandTask[1].split("/from|/to");
        Task curr = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        store.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + curr.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    private static void deleteTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + curr.toString());
        store.remove(curr);
        System.out.println("Now you have " + store.size() + " tasks in the list.");

    }
}
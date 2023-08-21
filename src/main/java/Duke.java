import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the program.
 */
public class Duke {

    /**
     * Name of the bot.
     */
    private final String NAME = "Cabbage";
    /**
     * Dynamic array of tasks.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Entry-point for the program. Displays a greeting, echos the user's input, and exits.
     * @param args CLI arguments passed into the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                duke.listTasks();
            }
            // begins with mark
            else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                duke.tasks.get(index - 1).markAsDone();
            }
            // begins with unmark
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                duke.tasks.get(index - 1).markAsUndone();
            }
            else {
                duke.addTask(input);
            }
            input = scanner.nextLine();
        }
        duke.exit();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Echoes the user's input.
     * @param input The user's input.
     */
    public void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(String task) {
        if (task.startsWith("todo")) {
            tasks.add(new ToDo (task.substring(5)));
        }
        else if (task.startsWith("deadline")) {
            String[] split = task.substring(9).split("/by");
            tasks.add(new Deadline(split[0], split[1]));
        }
        else if (task.startsWith("event")) {
            String[] split = task.substring(6).split("/");

            tasks.add(new Event(split[0], split[1].substring(5), split[2].substring(3)));
        }
        else {
            System.out.println("____________________________________________________________");
            System.out.println("Bro i'm not sure what that means, pls try again!");
            System.out.println("____________________________________________________________");
            return;
        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Lists all the tasks.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Exits the program.
     */
    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

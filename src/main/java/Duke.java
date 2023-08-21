import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    /**
     * Name of the bot.
     */
    private final String NAME = "Cabbage";
    /**
     * Dynamic array of tasks.
     */
    private final List<String> tasks = new ArrayList<>();

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
            } else {
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
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Lists all the tasks.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
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

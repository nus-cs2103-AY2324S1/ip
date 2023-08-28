import exception.DukeException;
import exception.UnknownCommandException;
import task.TaskList;
import java.util.Scanner;

/**
 * The main class of the program.
 */
public class Duke {

    /**
     * Name of the bot.
     */
    private final String NAME = "Snake CYQJ";
    /**
     * Dynamic array of tasks.
     */
    private final TaskList taskList = new TaskList();

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
            duke.selectCommand(input);
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
     * Select command to run.
     * @param input The user's input.
     */
    public void selectCommand(String input) {
        try {
            if (input.equals("list")) {
                this.taskList.listTasks();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                this.taskList.markAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                this.taskList.markAsUndone(index);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                this.taskList.addTask(input);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                this.taskList.deleteTask(index);
            }
            else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
        }
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

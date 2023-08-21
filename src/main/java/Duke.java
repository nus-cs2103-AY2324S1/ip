import exception.DukeException;
import exception.IllegalTaskIndexException;
import exception.InvalidArgumentException;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

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
                this.listTasks();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                this.markAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                this.markAsUndone(index);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                String[] description = input.split(" ");
                if (description.length <= 1) {
                    throw new InvalidArgumentException("☹ OOPS!!! The description cannot be empty.");
                }
                this.addTask(input);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                this.deleteTask(index);
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
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     * @throws InvalidArgumentException If the task's format is invalid.
     */
    public void addTask(String task) throws InvalidArgumentException {
        if (task.startsWith("todo")) {
            // Check if task description contains only spaces
            tasks.add(new ToDo(task.substring(5)));
        }
        else if (task.startsWith("deadline")) {
            String[] split = task.substring(9).split("/by");
            if (!task.matches("deadline\\s.+\\s+/by\\s+.+")) {
                throw new InvalidArgumentException("☹ OOPS!!! task.Deadline format is incorrect. " +
                        "It should be: deadline <name> /by <dateTime>");
            }
            if (split.length <= 1) {
                throw new InvalidArgumentException("Please specify in this format: " +
                        "deadline <description> /by <date & time>");
            }
            tasks.add(new Deadline(split[0], split[1]));
        }
        else if (task.startsWith("event")) {
            if (!task.matches("event\\s.+\\s+/from\\s.+\\s+/to\\s.+")) {
                throw new InvalidArgumentException("☹ OOPS!!! The event format is incorrect. " +
                        "It should be: event <name> /from <dateTime> /to <dateTime>");
            }
            String[] split = task.substring(6).split("/");
            tasks.add(new Event(split[0], split[1].substring(5), split[2].substring(3)));

        }
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
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
        if (this.tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + "." + this.tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void markAsDone(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        this.tasks.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void markAsUndone(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        this.tasks.get(index - 1).markAsUndone();
    }

    /**
     * Delete a task from the list of tasks.
     * @param index The index of the task to be deleted.
     * @throws IllegalTaskIndexException If the index is invalid.
     */
    public void deleteTask(int index) throws IllegalTaskIndexException {
        if (index > tasks.size() || index < 1) {
            throw new IllegalTaskIndexException();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        String placeholder = tasks.size() == 1 ? "task" : "tasks";
        System.out.println("Now you have " + tasks.size() + " " + placeholder + " in the list.");
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

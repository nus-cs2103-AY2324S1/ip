import java.util.Scanner;
import java.util.Map;
import exceptions.ThorndikeException;

public class Thorndike {
    Scanner scanner;
    Task[] list;
    int index;
    Boolean running;

    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.list = new Task[100];
        this.index = 0;
        this.running = true;
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        greet();
        while (running) {
            try {
                listen();
            } catch (ThorndikeException e) {
                echo(e.getMessage());
            }
        }
    }

    /**
     * Listens to command given to user.
     */
    private void listen() throws ThorndikeException {
        System.out.print(">> ");
        String input = scanner.nextLine();
        Map<String, String> args = CommandParser.parse(input);

        String command = args.get("command");
        String description = args.get("description");

        if (command.equals("list")) {
            list();
        } else if (command.equals("bye")) {
            exit();
        } else if (command.equals("mark")) {
            int idx = Integer.parseInt(description);
            if (idx < 1 || idx > index) {
                throw new ThorndikeException("The index given is invalid");
            }
            markDone(idx);
        } else if (command.equals("unmark")) {
            int idx = Integer.parseInt(description);
            if (idx < 1 || idx > index) {
                throw new ThorndikeException("The index given is invalid");
            }
            markNotDone(idx);
        } else if (command.equals("todo")) {
            if (description.equals("")) {
                throw new ThorndikeException("The description of a todo cannot be empty.");
            }
            addTask(new Todo(description));
        } else if (command.equals("deadline")) {
            if (description.equals("")) {
                throw new ThorndikeException("The description of a deadline cannot be empty.");
            }
            addTask(new Deadline(description, args.get("by")));
        } else if (command.equals("event")) {
            if (description.equals("")) {
                throw new ThorndikeException("The description of an event cannot be empty.");
            }
            addTask(new Event(description, args.get("from"), args.get("to")));
        } else {
            throw new ThorndikeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Marks a task as done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markDone(int idx) {
        echo("Meow! I've marked this task as done:");
        list[idx - 1].setDone();
        echo(list[idx - 1].toString());
    }

    /**
     * Marks a task as not done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markNotDone(int idx) {
        echo("Meow! I've marked this task as not done yet:");
        list[idx - 1].setNotDone();
        echo(list[idx - 1].toString());
    }

    /**
     * Add task to list.
     * 
     * @param task The task.
     * 
     */
    private void addTask(Task task) {
        this.list[index] = task;
        this.index++;
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo(String.format("Now you have %d tasks in the list.", index));
    }

    /**
     * Lists all items in the list.
     * 
     */
    private void list() {
        for (int i = 1; i < this.index + 1; i++) {
            Task task = this.list[i - 1];
            echo(String.format("%d. %s", i, task.toString()));
        }
    }

    /**
     * Prints to terminal.
     * 
     * @param str The message to print.
     */
    private void echo(String str) {
        System.out.println("    " + str);
    }

    /**
     * Sends greetings to user.
     */
    private void greet() {
        echo("Meow! I'm Thorndike.");
        echo("What can I do for you?");
    }

    /**
     * Terminates the chatbot.
     */
    private void exit() {
        this.running = false;
        echo("Bye meow! Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Thorndike chatbot = new Thorndike();
        chatbot.start();
    }
}
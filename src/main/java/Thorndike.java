import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import exceptions.InvalidCommandException;
import exceptions.InvalidIndexException;
import exceptions.MissingDescriptionException;
import exceptions.ThorndikeException;

public class Thorndike {
    Scanner scanner;
    ArrayList<Task> list;
    int index;
    Boolean running;

    public Thorndike() {
        this.scanner = new Scanner(System.in);
        this.list = new ArrayList<>();
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
            return;
        }

        if (command.equals("bye")) {
            exit();
            return;
        }

        if (command.equals("mark")) {
            int idx = getIndex(description);
            markDone(idx);
            return;
        }
        if (command.equals("unmark")) {
            int idx = getIndex(description);
            markNotDone(idx);
            return;
        }

        if (command.equals("todo")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("todo");
            }
            addTask(new Todo(description));
            return;
        }

        if (command.equals("deadline")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("deadline");
            }
            addTask(new Deadline(description, args.get("by")));
            return;
        }

        if (command.equals("event")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("event");
            }
            addTask(new Event(description, args.get("from"), args.get("to")));
            return;
        }

        if (command.equals("delete")) {
            int idx = getIndex(description);
            deleteTask(idx);
            return;
        }

        throw new InvalidCommandException();
    }

    /**
     * Marks a task as done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markDone(int idx) {
        echo("Meow! I've marked this task as done:");
        list.get(idx - 1).setDone();
        echo(list.get(idx - 1).toString());
    }

    /**
     * Marks a task as not done.
     * 
     * @param idx Index of the task.
     * 
     */
    private void markNotDone(int idx) {
        echo("Meow! I've marked this task as not done yet:");
        list.get(idx - 1).setNotDone();
        echo(list.get(idx - 1).toString());
    }

    /**
     * Adds task to list.
     * 
     * @param task The task.
     * 
     */
    private void addTask(Task task) {
        this.list.add(task);
        this.index++;
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo(String.format("Now you have %d tasks in the list.", index));
    }

    /**
     * Deletes task in list.
     * 
     * @param index Index of task in the list.
     * 
     */
    private void deleteTask(int index) {
        Task deleted = this.list.get(index - 1);
        this.list.remove(index - 1);
        this.index--;
        echo("Meow. I've removed this task:");
        echo(deleted.toString());
        echo(String.format("Now you have %d tasks in the list.", this.index));
    }

    /**
     * Lists all items in the list.
     * 
     */
    private void list() {
        echo("Here are the tasks in your list:");
        for (int i = 1; i < this.index + 1; i++) {
            Task task = this.list.get(i - 1);
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

    /**
     * Checks if the index has
     * 
     * @param idx Index to check.
     */
    private int getIndex(String index) throws ThorndikeException {
        int idx = -1;
        try {
            idx = Integer.parseInt(index);
        } catch (Exception e) {
            throw new InvalidIndexException();
        }
        if (idx < 1 || idx > this.index) {
            throw new InvalidIndexException();
        }
        return idx;
    }

    public static void main(String[] args) {
        Thorndike chatbot = new Thorndike();
        chatbot.start();
    }
}
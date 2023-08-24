import java.util.Scanner;

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
            listen();
        }
    }

    /**
     * Listens to command given to user.
     */
    private void listen() {
        System.out.print(">> ");
        String input = scanner.nextLine();
        String command = input.split(" ")[0];

        if (input.equals("list")) {
            list();
        } else if (input.equals("bye")) {
            exit();
        } else if (isMarkCommand(input)) {
            int idx = Integer.parseInt(input.split(" ")[1]);
            if (command.equals("mark")) {
                echo("Meow! I've marked this task as done:");
                list[idx - 1].setDone();
            } else {
                echo("Meow, I've marked this task as not done yet:");
                list[idx - 1].setNotDone();
            }
            echo(list[idx - 1].toString());
        } else if (isAddCommand(input)) {
            if (command.equals("todo")) {
                addTask(new Todo(input.split(" ")[1]));
            } else if (command.equals("deadline")) {
                addTask(new Deadline(input.split(" ")[1], input.split("/by ")[1]));
            } else if (command.equals("event")) {
                String time = input.split("/from ")[1];
                String from = time.split("/to ")[0];
                String to = time.split("/to ")[1];
                addTask(new Event(input.split(" ")[1], from, to));
            }
        } else {
            echo("Invalid");
        }
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
     * Determines if the input is a valid mark/unmark command
     * 
     * @param input Input.
     * @return true if valid, false is invalid.
     * 
     */
    private boolean isMarkCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];
        if (command.equals("mark") || command.equals("unmark")) {
            try {
                int number = Integer.parseInt(parts[1]);
                if (parts.length == 2 && number > 0 && number <= index) {
                    return true;
                }
            } catch (NumberFormatException e) {

            }
        }
        return false;
    }

    /**
     * Determines if the input is a valid mark/unmark command
     * 
     * @param input Input.
     * @return true if valid, false is invalid.
     * 
     */
    private boolean isAddCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];
        if (command.equals("todo")) {
            return true;
        }
        if (command.equals("deadline") && input.split("/by").length == 2) {
            return true;
        }
        if (command.equals("event") && input.split("/from").length == 2 && input.split("/to").length == 2) {
            return true;
        }
        return false;
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
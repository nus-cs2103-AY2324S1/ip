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
        } else {
            this.list[index] = new Task(input);
            this.index++;
            echo(String.format("added: %s", input));
        }

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
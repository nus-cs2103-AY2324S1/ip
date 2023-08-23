import java.util.Scanner;

public class Thorndike {
    Scanner scanner;
    String[] list;
    int index;
    Boolean running;

    public Thorndike() {
        this.scanner = new Scanner(System.in);
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
        String command = scanner.nextLine();

        switch (command) {
            case "bye":
                exit();
                break;
            default:
                echo(command);
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

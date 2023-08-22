import java.util.Scanner;

public class Duke {
    /** The name of the chatbot. */
    private static final String NAME = "Bro";

    /** The scanner for the chatbot. */
    private Scanner scanner;

    /** Constructor for the chatbot. */
    public Duke() {
        this.scanner = new Scanner(System.in);
    }

    /** Greets the user. */
    public void greet() {
        System.out.println("Hello! I'm " + NAME + "\n" + "What can I do for you? \n");
    }

    /** Causes chatbot to exit. */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Echoes commands entered by user.
     *
     * @param input The command entered by the user.
     */
    public void echo(String input) {
        System.out.println(input + "\n");
    }

    /** Reads user input. Exits if user input is "bye", else, echoes commands. */
    public void readInput() {
        System.out.print("> ");
        String userInput = this.scanner.nextLine();

        if (userInput.equals("bye")) {
            this.exit();
        } else {
            this.echo(userInput);
            this.readInput();
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.readInput();
    }
}

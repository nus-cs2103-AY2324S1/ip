import java.util.Scanner;

/**
 * User interface class responsible for dealing with interactions with the user.
 */
public class Ui {
    /**
     * Scanner to read user input.
     */
    private final Scanner sc = new Scanner(System.in);
    /**
     * Prints the error to the screen.
     *
     * @param str Error message.
     */
    public void showError(String str) {
        System.out.println("ERROR: " + str);
    }

    /**
     * Prints the message to the screen.
     *
     * @param str Message.
     */
    public void println(String str) {
        System.out.println(str);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }
    public void showWelcome() {
        println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
    }
    public void showGoodbye() {
        println("Bye. I'll be at the nearest trash can!");
    }
}

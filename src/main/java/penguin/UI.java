package penguin;

import java.util.Scanner;

/**
 * UI handles the I/O functionality of Penguin chatbot.
 */
public class UI {

    private static final String GREETING = "Honk! I'm Penguin! What can I do for you?";

    private Scanner input;

    public UI() {
        this.input = new Scanner(System.in);
    }
    /**
     * Obtains the next command input by the user.
     *
     * @return Next command from the user.
     */
    public String in() {
        return input.nextLine();
    }

    /**
     * Outputs a message for the user.
     *
     * @param msg Message to be output to the user.
     */
    public void out(String msg) {
        System.out.println(msg);
    }

}

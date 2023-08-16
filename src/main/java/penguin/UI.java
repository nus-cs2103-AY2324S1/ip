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

    public String in() {
        return input.nextLine();
    }

    public void out(String msg) {
        System.out.println(msg);
    }

}

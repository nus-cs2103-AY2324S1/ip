package dude;

import java.util.Scanner;

/**
 * User interface.
 */
public class Ui {

    // Messages
    private static final String LOGO =
            " _|    _| _    O\n" +
                    "(_||_|(_|(/_  /İ\\\n" +
                    "------------  ```\n";
    private static final String BORDER = "-----------------------------------------------------------\n";
    private static final String HELLO_MSG = LOGO +
            "Hello! I'm dude.\n" +
            "What can I do for you?";

    private final Scanner sc;

  /**
   * Constructor for Ui class.
   */
  public Ui() {
    sc = new Scanner(System.in);
  }

    /**
     * Formats and prints given message/prompt to console.
     *
     * @param message Message to print. Lines separated by \n.
     */
    public void printMessage(String message) {
        String[] lines = message.split("\\n");
        String prefix = "  ";
        String output = BORDER + prefix +
                String.join("\n" + prefix, lines) +
                "\n" + BORDER;
        System.out.println(output);
    }

    /**
     * Prints startup message.
     */
    public void printHello() {
        printMessage(HELLO_MSG);
    }

    /**
     * Reads user text input.
     *
     * @return String input from user.
     */
    public String readInput() {
        // read user input
        return sc.nextLine();
    }
}

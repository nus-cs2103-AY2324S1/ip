package duke;

import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 */
public class Ui {
    private static final String LINE_BREAK = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";

    private Scanner sc;

    /**
     * Constructs a Ui object and initializes the scanner to read user input from the console.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the given message between horizontal lines
     *
     * @param msg String to output
     */
    public String displayMessage(String msg) {
        String indentedMsg = indent(msg);
        System.out.println(LINE_BREAK);
        System.out.println(indentedMsg);
        System.out.println(LINE_BREAK);

        return msg;
    }

    /**
     * Displays an error message with the "OOPS!!!" prefix.
     *
     * @param msg Error message to be displayed
     * @return The indented error message that was displayed
     */
    public String displayError(String msg) {
        return displayMessage("☹ OOPS!!! \n" + msg);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a String
     */
    public String readNextInput() {
        return sc.nextLine();
    }

    private static String indent(String msg) {
        return msg.replaceAll("(?m)^", "\t");
    }
}

package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Ui class.
 * Handles printing Duke's messages and errors.
 * Also helps to add colour to the text printed.
 */
public class Ui {
    /**
     * The COLOR enum.
     * Contains all the allowed ANSI color codes
     * used by Duke.
     */
    public static enum Color {
        RESET("\u001B[0m"),
        GREEN("\033[0;32m"),
        YELLOW("\033[0;33m"),
        BLUE("\033[0;34m"),
        PURPLE("\u001B[35m"),
        RED("\033[0;31m");

        public final String value;

        private Color(String value) {
            this.value = value;
        }
    }

    private static BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in)
    );

    /**
     * This method wraps the given text with the given
     * ANSI color codes.
     * 
     * @param text The text to be colored.
     * @param color The color to be applied.
     * @return A string with the necessary color codes inserted.
     */
    public static String cTxt(String text, Color color) {
        return color.value + text + Color.RESET.value;
    }

    /**
     * Displays the introduction message when Duke starts.
     */
    public void displayIntro() {
        displayMsg(new String[] {
            "Hi. I'm " + Ui.cTxt("Bryan", Ui.Color.PURPLE),
            "What can I do for you?"
        });
    }

    /**
     * Displays the goodbye message when exiting Duke.
     */
    public void displayGoodbye() {
        displayMsg("Bye~ Come back soon :)");
    }

    /**
     * Displays the first character on the input line.
     * Indicates where the user is typing his command.
     */
    public void displayInputStart() {
        System.out.print("> ");
    }

    /**
     * Reads the user input.
     * 
     * @return A string containing the user input.
     * @throws IOException Thrown when there's an issue
     *                     with reading user input.
     */
    public String readInput() throws IOException {
        return reader.readLine();
    }

    /**
     * Formats and displays a single line message.
     * 
     * @param text The message to be displayed.
     */
    public void displayMsg(String text) {
        String msg = String.format("\n    %s", text);
        System.out.println(msg + "\n");
    }

    /**
     * Formats and displays a multi-line message.
     * 
     * @param text An array of messages to be displayed.
     */
    public void displayMsg(String[] text) {
        String msg = "\n";
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }

    /**
     * Formats and displays a single line error message.
     * 
     * @param text The error message to be displayed.
     */
    public void displayError(String text) {
        String msg = String.format(
            "\n    %s\n    %s\n",
            cTxt("Erm... error :(", Color.RED),
            text
        );
        System.out.println(msg);
    }

    /**
     * Formats and displays a multi-line error message.
     * 
     * @param text An array of error message to be displayed.
     */
    public void displayError(String[] text) {
        String msg = String.format(
            "\n    %s\n",
            cTxt("Erm... error :(", Color.RED)
        );
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }
}

package duke;

import java.util.Scanner;

/**
 * The UI class handles user interactions and printing messages in the Duke application.
 */
public class UI {
    private final String logo = "       ___\n"
            + "    . -^   `--,\n"
            + "   /# =========`-_\n"
            + "  /# (--====___====\\\n"
            + " /#   .- --.  . --.|\n"
            + "/##   |  * ) (   * ),\n"
            + "|##   \\    /\\ \\   / |\n"
            + "|###   ---   \\ ---  |\n"
            + "|####      ___)    #|\n"
            + "|######           ##|\n"
            + " \\##### ---------- /\n"
            + "  \\####           (\n"
            + "   `\\###          |\n"
            + "     \\###         |\n"
            + "      \\##        |\n"
            + "       \\###.    .)\n"
            + "        `======/";

    /**
     * Constructs a UI object.
     */
    public UI() {
    }

    /**
     * Prints the introduction message with the Duke logo.
     */
    public void printIntro() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm HEAD");
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user input as a string.
     */
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("SHOW ME WHAT YOU'VE GOT");
        String input = sc.nextLine();
        return input;
    }
}

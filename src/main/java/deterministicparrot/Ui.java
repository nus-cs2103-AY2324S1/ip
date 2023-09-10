package deterministicparrot;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the application.
 */
public class Ui {
    private Scanner s;
    private StringWriter sw;
    private PrintWriter pw;

    /**
     * Constructs a Ui instance with the provided Scanner and PrintWriter.
     *
     * @param s  The Scanner to read input from the user.
     * @param pw The PrintWriter to output messages to the user.
     */
    public Ui(Scanner s, StringWriter sw) {
        this.s = s;
        this.sw = sw;
        this.pw = new PrintWriter(sw, false);
    }

    /**
     * Constructs a Ui instance with default System.in and System.out.
     */
    public Ui() {
        this.s = new Scanner(System.in);
        this.pw = new PrintWriter(System.out, true);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return s.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        this.printDash();
        this.pw.println("     " + "Hello! I'm deterministicparrot.DeterministicParrot");
        this.pw.println("     " + "What can I do for you?");
        this.printDash();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void bye() {
        this.pw.println("     " + "Bye. Hope to see you again soon!");
    }

    /**
     * Reads and trims a line of input from the user.
     *
     * @return The trimmed user input.
     */
    public String readLine() {
        return this.s.nextLine().trim();
    }

    /**
     * Prints a string followed by a new line.
     *
     * @param s The string to be printed.
     */
    public void println(String s) {
        this.pw.println(s);
    }

    /**
     * Prints an error message from an exception.
     *
     * @param e The exception containing the error message.
     */
    public void printError(Exception e) {
        this.pw.println(e.getMessage());
    }

    /**
     * Prints a dashed line separator.
     */
    public void printDash() {
        this.pw.println("    ____________________________________________________________");
    }

    public void inputText(String str) {
        this.s = new Scanner(str); //bad and not performant, but works
    }

    public String flushWriter() {
        String ans = this.sw.toString();
        System.out.println(ans);
        this.sw.getBuffer().setLength(0);
        return ans;
    }
}

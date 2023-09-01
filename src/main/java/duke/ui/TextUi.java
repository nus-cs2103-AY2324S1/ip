package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    private static final int INDENT_SIZE = 4;
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    private static String indent(String s) {
        return String.format("%s%s", " ".repeat(INDENT_SIZE), s);
    }

    /**
     * Outputs strings to terminal, indenting each string using the `indent` method. If a string is a multi-line
     * string, i.e. contains the `\n` character, each line is split and indented individually.
     *
     * @param strings a list of strings to be output to the terminal
     */
    public void whisper(String... strings) {
        for (String string : strings) {
            for (String s : string.split("\n")) {
                out.println(indent(s));
            }
        }
    }

    /**
     * Outputs string to the terminal, indenting each string using the `indent` method. An extra empty line is added
     * to the end of the strings. If a string is a multi-line string, i.e. contains the `\n` character, each line is
     * split and indented individually.
     *
     * @param strings a list of strings to be output to the terminal
     */
    public void say(String... strings) {
        whisper(strings);
        out.println();
    }

    /**
     * Returns the input from the user, with leading and trailing removed.
     *
     * @return the string input from the user
     */
    public String getInput() {
        return in.nextLine().trim();
    }

    /**
     * Greets the user, and asks the user for a command.
     *
     * @param me the name the program wants to be known as
     */
    public void greet(String me) {
        say(String.format("Hello! I'm %s", me), "What can I do for you?");
    }

}

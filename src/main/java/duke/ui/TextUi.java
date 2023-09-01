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

    public void whisper(String... strings) {
        for (String string : strings) {
            for (String s : string.split("\n")) {
                out.println(indent(s));
            }
        }
    }

    public void say(String... strings) {
        whisper(strings);
        out.println();
    }

    public String getInput() {
        return in.nextLine().trim();
    }

    public void greet(String me) {
        say(String.format("Hello! I'm %s", me), "What can I do for you?");
    }

}

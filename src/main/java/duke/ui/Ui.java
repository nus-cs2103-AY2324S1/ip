package duke.ui;

/**
 * Static class that handles printing of formatted messages to the user.
 */
public class Ui {
    /**
     * Constants used for formatting messages.
     */
    //@formatter:off
    public static final String TAB = "    ";
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO = """
            _______         _
            |  ____|       (_)
            | |__ ___  _ __ _ _ __   ___
            |  __/ _ \\| '__| | '_ \\ / _ \\
            | | | (_) | |  | | | | |  __/
            |_|  \\___/|_|  |_|_| |_|\\___|
                            """;
    //@formatter:on

    public Ui() { }

    /**
     * Prints a message with a tab in front.
     *
     * @param s The message to be printed.
     */
    public static String printWithTab(String s) {
        System.out.println(TAB + s);
        return TAB + s;
    }

    /**
     * Prints a line.
     */
    public static String printLine() {
        System.out.println(TAB + LINE);
        return TAB + LINE;
    }

    /**
     * Prints the opening message.
     */
    public static String opener() {
        String outputString = "";
        outputString += LOGO;
        outputString += printWithTab("Hello! I'm Forine");
        outputString += printWithTab("What can I do for you?");
        printInLine(outputString);
        return outputString;
    }

    /**
     * Prints a message with a line above and below.
     *
     * @param s The message to be printed.
     */
    public static void printInLine(String s) {
        printLine();
        String[] lines = s.split("\\r?\\n");
        for (String line : lines) {
            printWithTab(line);
        }
        printLine();
    }
}

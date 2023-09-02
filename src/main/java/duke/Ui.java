package duke;

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

    /**
     * Prints a message with a tab in front.
     * 
     * @param s The message to be printed.
     */
    public static void printWithTab(String s) {
        System.out.println(TAB + s);
    }

    /**
     * Prints a line.
     */
    public static void printLine() {
        System.out.println(TAB + LINE);
    }

    /**
     * Prints the opening message.
     */
    public static void opener() {
        printWithTab(LOGO);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");
        printWithTab(LINE);
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

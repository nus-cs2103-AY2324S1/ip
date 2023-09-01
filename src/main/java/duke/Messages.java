package duke;

/*
 * Static class that handles printing of formatted messages to the user.
 */
public class Messages {
    public static final String TAB = "    ";
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO = 
    """
        _______         _
            |  ____|       (_)
            | |__ ___  _ __ _ _ __   ___
            |  __/ _ \\| '__| | '_ \\ / _ \\
            | | | (_) | |  | | | | |  __/
            |_|  \\___/|_|  |_|_| |_|\\___|
                          """;

    public static void printWithTab(String s) {
        System.out.println(TAB + s);
    }

    public static void printLine() {
        System.out.println(TAB + LINE);
    }

    public static void opener() {
        printWithTab(LOGO);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");
        printWithTab(LINE);
    }

    public static void printInLine(String s) {
        printLine();
        String[] lines = s.split("\\r?\\n");
        for (String line : lines) {
            printWithTab(line);
        }
        printLine();
    }
}

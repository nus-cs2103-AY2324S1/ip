package gui;

/**
 * This class handles the UI on the terminal. Obsolete now that we have the GUI.
 */
public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String GREETING_MESSAGE = LINE_SEPARATOR + "\n"
            + " Hello! I'm IRIS\n"
            + " What can I do for you?\n"
            + LINE_SEPARATOR;
    private static final String GOODBYE_MESSAGE = LINE_SEPARATOR + "\n"
            + "Bye. Hope to see you again soon!\n"
            + LINE_SEPARATOR;

    /**
     * Prints the introductory message onto the terminal.
     */
    public void printIntroMsg() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Prints the outro message onto the terminal.
     */
    public void printOutroMsg() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Prints the line separators onto the terminal.
     */
    public void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

}

package ui;

/**
 * The Ui class deals with user interactions by converting the output of the chatbot
 * to a more user-friendly output.
 */
public class Ui {
    // Basic UI & messages for the chatbot to print to console
    private static final String HORIZONTAL_LINE = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    private static final String INTRO = "Hello there! I'm Ekud. :)\n" +
            "What can I do for you? :O";
    private static final String OUTRO = "Goodbye, have a nice day! :p";

    /**
     * Prints a message formatted in between 2 horizontal lines for the user.
     * @param message Text to be printed.
     */
    public static void printMsg(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                HORIZONTAL_LINE,
                message,
                HORIZONTAL_LINE));
    }

    /**
     * Prints the horizontal line divider.
     */
    public static void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the chatbot's intro message.
     */
    public static void intro() {
        printMsg(INTRO);
    }

    /**
     * Prints the chatbot's outro message.
     */
    public static void outro() {
        printMsg(OUTRO);
    }

}

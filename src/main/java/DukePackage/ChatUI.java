package DukePackage;

/**
 * The ChatUI class represents the user interface for the chatbot application.
 */
public class ChatUI {

    private final String HORIZONTAL_LINE = "   ------------------------------------------------------------------------";
    private final String INTRO = "    Hello! I'm iPbot \n" +
            "    What can I do for you?";
    private final String OUTRO = "    Bye. Hope to see you again soon!";

    /**
     * Constructs a new ChatUI object.
     */
    public ChatUI() {
        // Constructor body (if needed)
    }

    /**
     * Prints a horizontal line divider.
     */
    public void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the introduction message.
     */
    public void printIntro() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INTRO);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the outro message.
     */
    public void printOutro() {
        System.out.println(OUTRO);
    }
}
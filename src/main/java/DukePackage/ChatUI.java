package DukePackage;

/**
 * The ChatUI class represents the user interface for the chatbot application.
 */
public class ChatUI {

    String horizontalLine = "   ------------------------------------------------------------------------";
    String intro = "    Hello! I'm iPbot \n" +
            "    What can I do for you?";
    String outro = "    Bye. Hope to see you again soon!";

    /**
     * Constructs a new ChatUI object.
     */
    public ChatUI() {
        // Constructor body (if needed)
    }

    /**
     * Prints a horizontal line divider.
     */
    public void divider() {
        System.out.println(horizontalLine);
    }

    /**
     * Prints the introduction message.
     */
    public void intro() {
        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);
    }

    /**
     * Prints the outro message.
     */
    public void outro() {
        System.out.println(outro);
    }
}
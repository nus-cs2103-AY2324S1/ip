package services;

/**
 * Represents the user interface of Jarvis.
 */
public class TextUi {
    /**
     * The horizontal line for formatting messages.
     */
    private final static String HORIZONTAL_LINE = "__________________________________________________"
            + "_______________________\n";

    public void exit() {
        String exitMessage = "Goodbye, sir.\n" + "Shutting down...";
        new TextUi().print(exitMessage);
    }

    public void print(String message) {
        String messageWithHorizontalLine = HORIZONTAL_LINE + message + "\n" + HORIZONTAL_LINE;
        System.out.print(messageWithHorizontalLine);
    }

    public void greet() {
        String greetMessage = "At your service, sir.";
        print(greetMessage);
    }
}

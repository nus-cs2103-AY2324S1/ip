package devybot.util;

public class Ui {
    private static StringBuilder outputBuffer = new StringBuilder();

    /**
     * Captures the message to the user.
     * 
     * @param message The message to be displayed to the user.
     */
    public static void showMessage(String message) {
        outputBuffer.append(message).append("\n");
    }

    /**
     * Returns the output that has been captured by the Ui.
     * 
     * @return The output that has been captured by the Ui.
     */
    public static String captureOutput() {
        String capturedOutput = outputBuffer.toString();
        outputBuffer.setLength(0); // Clear the buffer
        return capturedOutput;
    }

    /**
     * Displays a greeting message to the user.
     */
    public static String greet() {
        return "Hello! I'm DevyBot\nWhat can I do for you?";

    }

    /**
     * Displays an exit message to the user.
     */
    public static String exit() {
        return ("Bye. Hope to see you again soon!");
    }
}

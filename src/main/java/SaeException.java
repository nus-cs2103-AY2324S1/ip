/**
 * Custom exception class for handling errors specific to the Sae chatbot.
 */
public class SaeException extends Exception {

    /**
     * Returns a formatted error message to indicate that the chatbot is unable to understand the input.
     *
     * @return A string containing the error message.
     */
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

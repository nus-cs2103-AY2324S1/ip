package duke.util;

/**
 * Represents a response from chatbot.
 * A <code>Response</code> object corresponds to a message and
 * a boolean value indicating whether it is an error respond.
 */
public class Response {

    private String message;
    private boolean isError;
    private boolean isExit;

    /**
     * Constructs a <code>Response</code> object.
     *
     * @param message The message of the response.
     * @param isError Whether the response is an error response.
     */
    public Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
        this.isExit = false;
    }

    /**
     * Constructs a <code>Response</code> object, that is not an error response.
     *
     * @param message The message of the response.
     */
    public Response(String message) {
        this(message, false);
    }

    /**
     * Constructs a <code>Response</code> object.
     *
     * @param message The message of the response.
     * @param isError Whether the response is an error response.
     * @param isExit Whether the response is an exit response.
     */
    public Response(String message, boolean isError, boolean isExit) {
        this.message = message;
        this.isError = isError;
        this.isExit = isExit;
    }

    /**
     * Connects multiple responses into one. The message of the new
     * response is the concatenation of the messages of the given responses.
     * If any of the given responses is an error response, the new response
     * is also an error response.
     *
     * @param responses The responses to be connected.
     * @return The connected response.
     */
    public static Response connectResponses(Response... responses) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isError = false;
        for (Response response : responses) {
            stringBuilder.append(response.message);
            isError = isError || response.isError;
        }
        return new Response(stringBuilder.toString(), isError);
    }

    /**
     * Returns whether the response is an error response.
     *
     * @return True if the response is an error response, false otherwise.
     */
    public boolean isErrorResponse() {
        return isError;
    }

    /**
     * Returns whether the response is an exit response.
     *
     * @return True if the response is an exit response, false otherwise.
     */
    public boolean isExitResponse() {
        return isExit;
    }

    /**
     * Returns the string representation of the response.
     *
     * @return the string representation of the response.
     */
    @Override
    public String toString() {
        return message;
    }
}

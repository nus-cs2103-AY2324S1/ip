package rock.client;

/**
 * Represents a response created
 * by the client to be displayed
 * on the GUI
 * @author Alvis Ng (supermii2)
 */
public class Response {
    private final String response;
    private boolean isError;
    private Response(String responseMessage) {
        this.response = responseMessage;
    }

    /**
     * Creates a valid response object
     * for the GUI to handle
     * @param msg Message of the response
     * @return Corresponding response object
     */
    public static Response createValidResponse(String msg) {
        Response r = new Response(msg);
        r.isError = false;
        return r;
    }

    /**
     * Creates an error response object
     * for the GUI to handle
     * @param msg Message of the error
     * @return Corresponding response object
     */
    public static Response createErrorResponse(String msg) {
        Response r = new Response(msg);
        r.isError = true;
        return r;
    }

    /**
     * Gets the message associated
     * with the response
     * @return Message of the response
     */
    public String getMessage() {
        return this.response;
    }

    /**
     * Returns true if the message
     * is an error
     * @return True if error, false otherwise
     */
    public boolean isError() {
        return this.isError;
    }
}

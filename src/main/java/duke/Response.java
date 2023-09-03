package duke;

/**
 * A response object that stores the response string.
 */
public class Response {
    private final String content;

    /**
     * Constructor for Response.
     * @param content  the content of the response.
     */
    public Response(String content) {
        this.content = content;
    }

    /**
     * Returns the content of the response.
     * @return  the content of the response.
     */
    public String getResponse() {
        return this.content;
    }
}

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

    public static Response createValidResponse(String msg) {
        Response r = new Response(msg);
        r.isError = false;
        return r;
    }
    public static Response createErrorResponse(String msg) {
        Response r = new Response(msg);
        r.isError = true;
        return r;
    }

    public String getMessage() {
        return this.response;
    }
    public boolean isError() {
        return this.isError;
    }
}

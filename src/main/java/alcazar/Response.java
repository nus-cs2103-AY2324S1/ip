package alcazar;

/**
 * Encapsulates a commands response
 */
public class Response {
    /** Response generated from a command*/
    private String result;
    /** Boolean to checker whether the command is exit*/
    private boolean isExit;

    /**
     * Constructs a new Response
     * @param result the result of the command
     * @param isExit whether the command is exit
     */
    public Response(String result, boolean isExit) {
        this.result = result;
        this.isExit = isExit;
    }

    /**
     * Evaluates and returns the result in this Response
     * @return The result in this Response
     */
    public String getResult() {
        return this.result;
    }

    /**
     * Evaluates whether this Response was generated from an Exit command
     * @return Boolean represent whether this Response is by an Exit command
     */

    public boolean isUserExiting() {
        return isExit;
    }

}

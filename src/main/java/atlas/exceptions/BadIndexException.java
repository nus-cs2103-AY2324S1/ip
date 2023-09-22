package atlas.exceptions;

/**
 * Exception for bad indices (either non-numeric or negative)
 */
public class BadIndexException extends RuntimeException {
    private final String badIndexStr;

    /**
     * Constructor for a BadIndexException
     * @param badIndexStr String containing the bad index input
     */
    public BadIndexException(String badIndexStr) {
        this.badIndexStr = badIndexStr;
    }

    @Override
    public String getMessage() {
        return "I asked for an index and you gave me " + badIndexStr + "? I may be an expert mathematician"
                + " but even I cannot turn this into an integer greater than zero.";
    }
}

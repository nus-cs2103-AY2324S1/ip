package atlas.exceptions;

/**
 * Exception for datetimes that cannot be parsed due to bad formatting or invalid values
 */
public class BadDateTimeException extends RuntimeException {
    private final String badDateTime;

    /**
     * Constructor for a BadDateTimeException
     * @param badDateTime String containing datetime to parse
     */
    public BadDateTimeException(String badDateTime) {
        this.badDateTime = badDateTime;
    }

    @Override
    public String getMessage() {
        return "Foolish mortal, the Tower of the Winds do not recognise the datetime "
                + badDateTime + ". Dates must be of the format "
                + "DD-MM-YYYY HHmm";
    }
}

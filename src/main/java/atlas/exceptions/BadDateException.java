package atlas.exceptions;

/**
 * Exception for dates that cannot be parsed due to either bad formats or invalid values
 */
public class BadDateException extends RuntimeException {
    private final String badDate;

    /**
     * Constructor for a BadDateException
     * @param badDate String containing date to parse
     */
    public BadDateException(String badDate) {
        this.badDate = badDate;
    }

    @Override
    public String getMessage() {
        return "Foolish mortal, Delphi records of no such date " + badDate + ". Dates must be of the format "
                + "DD-MM-YYYY";
    }
}

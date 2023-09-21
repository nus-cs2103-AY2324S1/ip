package common;

/**
 * Utility class for getting the exception messages that Thorndike uses.
 *
 * @author Ho Khee Wei
 */
public abstract class ExceptionMessage {
    public static final String INVALID_DATETIME = "The given date and time format is wrong!"
            + "Use [dd-mm-yyyy hhmm] or any other accepted formats";
    public static final String INVALID_INDEX = "The index given is invalid! Send 'list' to view the tasklist.";
    public static final String INVALID_PRIORITY = "The priority given is invalid, please a integer between 0 and 5.";
    public static final String MISSING_DESCRIPTION = "The description of a %s cannot be empty.";
}

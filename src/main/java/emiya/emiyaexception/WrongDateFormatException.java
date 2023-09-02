package emiya.emiyaexception;


/**
 * An exception that is thrown when the date that the user gives is in the wrong format.
 */
public class WrongDateFormatException extends EmiyaException {
    public WrongDateFormatException() {
        super("-----------------------------------------\n"
                + "Please give the date in the format of YYYY-MM-DD TTTT, "
                + "where T represents the time in the 24 hour format\n"
                + "-----------------------------------------\n");
    }
}

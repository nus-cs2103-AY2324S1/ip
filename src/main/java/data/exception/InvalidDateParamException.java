package data.exception;

/**
 * Custom exception class to indicate an
 * invalid date parameter was passed in
 * by the user when creating the relevant tasks.
 */
public class InvalidDateParamException extends DukeException {
    private static final String msg =
            "Oops, looks like your date is in an invalid format...\n"
            + "Here are some valid formats:\n"
            + "2023-10-20, 20-10-2023, 2023/10/20, "
            + "20/10/2023, Oct 10 2023, 10 Oct 2023\n"
            + "You can provide a timing as well: 2023-10-20 1800";

    public InvalidDateParamException() {
        super(msg);
    }

    @Override
    public String toString() {
        return msg;
    }
}

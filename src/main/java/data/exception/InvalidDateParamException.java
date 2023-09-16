package data.exception;

/**
 * Custom exception class to indicate an
 * invalid date parameter was passed in
 * by the user when creating the relevant tasks.
 */
public class InvalidDateParamException extends Exception {
    private static final String msg =
            """
            Oops, looks like your date is in an invalid format...
            Here are some valid formats:
            2023-10-20, 20-10-2023, 2023/10/20, 20/10/2023, Oct 10 2023, 10 Oct 2023
            You can provide a timing as well: 2023-10-20 1800
            """;

    @Override
    public String toString() {
        return msg;
    }
}

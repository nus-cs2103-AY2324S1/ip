package seedu.dookie;

/**
 * Encapsulates the exception where event duration details are not inputted completely.
 */
 class IncompleteDurationException extends DookieException {
    public IncompleteDurationException() {
        super("\u2639 OOPS!!! Duration is not specified correctly.\n" +
                "Please input the duration in the following format:\n" +
                "/from DD-MM-YYYY TTTT /to DD-MM-YYYY TTTT");
    }
}

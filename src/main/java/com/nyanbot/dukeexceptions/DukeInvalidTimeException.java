package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a DukeExceptions.DukeInvalidTimeException. Raised when the user provides an invalid date.
 *
 * @author Tan Kerway
 */
public class DukeInvalidTimeException extends DukeException {
    private static final String INVALID_TIME_PREFIX_STRING = " Please specify a valid ";
    private static final String INVALID_TIME_SUFFIX_STRING = " date!";

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     * @param details additional details to add to the error message
     */
    public DukeInvalidTimeException(String details) {
        super(INVALID_TIME_PREFIX_STRING + details + INVALID_TIME_SUFFIX_STRING);
    }
    /**
     * Returns the String representation of a DukeExceptions.DukeInvalidTimeException class.
     *
     * @author Tan Kerway
     * @return the String representation of a DukeExceptions.DukeInvalidTimeException class
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

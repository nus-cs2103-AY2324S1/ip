package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a general Exception class for DukeLauncher.Duke.
 *
 * @author Tan Kerway
 */
public class DukeException extends Exception {
    private static final String GENERAL_EXCEPTION_STRING = "OOPS!!!";

    /**
     * Constructor for the DukeExceptions.DukeException class.
     *
     * @author Tan Kerway
     */
    public DukeException(String s) {
        super(GENERAL_EXCEPTION_STRING + s);
    }

    /**
     * Returns the string representation of a DukeExceptions.DukeException.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeExceptions.DukeException
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a general Exception class for DukeLauncher.Duke.
 *
 * @author Tan Kerway
 */
public class DukeException extends Exception {

    /**
     * Constructor for the DukeExceptions.DukeException class.
     *
     * @author Tan Kerway
     */
    public DukeException(String s) {
        super("OOPS!!!" + s);
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

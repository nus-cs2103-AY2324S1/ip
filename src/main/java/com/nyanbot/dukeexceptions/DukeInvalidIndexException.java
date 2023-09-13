package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a DukeExceptions.DukeInvalidIndexException class. Raised when invalid index is provided.
 *
 * @author Tan Kerway
 */
public class DukeInvalidIndexException extends DukeException {
    public static final String INVALID_INDEX_STRING = " Please enter a valid index!";

    /**
     * Constructor for the DukeExceptions.DukeInvalidIndexException exception.
     *
     * @author Tan Kerway
     */
    public DukeInvalidIndexException() {
        super(INVALID_INDEX_STRING);
    }

    /**
     * Returns the String representation of an invalid index.
     *
     * @author Tan Kerway
     * @return the String representation of an invalid index
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

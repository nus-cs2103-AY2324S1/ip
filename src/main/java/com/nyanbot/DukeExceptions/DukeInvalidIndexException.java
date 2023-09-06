package com.nyanbot.DukeExceptions;

/**
 * Encapsulates a DukeExceptions.DukeInvalidIndexException class. Raised when invalid index is provided.
 *
 * @author Tan Kerway
 */
public class DukeInvalidIndexException extends DukeException {

    /**
     * Constructor for the DukeExceptions.DukeInvalidIndexException exception.
     *
     * @author Tan Kerway
     */
    public DukeInvalidIndexException() {
        super(" Please enter a valid index!");
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

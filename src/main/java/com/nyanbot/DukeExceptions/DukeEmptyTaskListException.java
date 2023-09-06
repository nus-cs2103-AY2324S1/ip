package com.nyanbot.DukeExceptions;

/**
 * Encapsulates a DukeEmptyTaskList. Raised when the user wants to delete
 * from an empty tasklist.
 *
 * @author Tan Kerway
 */
public class DukeEmptyTaskListException extends DukeException {

    /**
     * Constructor for the DukeEmptyTaskList class.
     *
     * @author Tan Kerway
     */
    public DukeEmptyTaskListException() {
        super(" The tasks list is empty!");
    }

    /**
     * Returns the String representation of a DukeExceptions.DukeEmptyTaskListException.
     *
     * @author Tan Kerway
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

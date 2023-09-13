package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a DukeEmptyTaskList. Raised when the user wants to delete
 * from an empty tasklist.
 *
 * @author Tan Kerway
 */
public class DukeEmptyTaskListException extends DukeException {
    private static final String EMPTY_TASK_LIST_STRING = " The tasks list is empty!";

    /**
     * Constructor for the DukeEmptyTaskList class.
     *
     * @author Tan Kerway
     */
    public DukeEmptyTaskListException() {
        super(EMPTY_TASK_LIST_STRING);
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

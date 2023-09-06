package com.nyanbot.DukeExceptions;

/**
 * Encapsulates a DukeExceptions.DukeEmptyInputException class. Raised when the user provides a command
 * without a task.
 *
 * @author Tan Kerway
 */
public class DukeEmptyInputException extends DukeException {

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     * @param command the command that the user typed in
     */
    public DukeEmptyInputException(String command) {
        super(" The description of a " + command + " cannot be empty.");
    }

    /**
     * Returns the string representation of a DukeExceptions.DukeEmptyInputException class.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeExceptions.DukeEmptyInputException class
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

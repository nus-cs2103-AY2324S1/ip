package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a DukeExceptions.DukeInvalidCommandException class. Raised when the user
 * does not provide a valid command to the chatbot.
 *
 * @author Tan Kerway
 */
public class DukeInvalidCommandException extends DukeException {
    private static final String INVALID_COMMAND_STRING = " I'm sorry, but I don't know what that means :3";
    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     */
    public DukeInvalidCommandException() {
        super(INVALID_COMMAND_STRING);
    }

    /**
     * Returns the string representation of a DukeExceptions.DukeInvalidCommandException class.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeExceptions.DukeInvalidCommandException class
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

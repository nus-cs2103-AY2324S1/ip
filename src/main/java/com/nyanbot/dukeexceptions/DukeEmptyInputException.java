package com.nyanbot.dukeexceptions;

/**
 * Encapsulates a DukeExceptions.DukeEmptyInputException class. Raised when the user provides a command
 * without a task.
 *
 * @author Tan Kerway
 */
public class DukeEmptyInputException extends DukeException {
    private static final String EMPTY_INPUT_PREFIX_STRING = " The description of a ";
    private static final String EMPTY_INPUT_SUFFIX_STRING = " cannot be empty.";

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     * @param command the command that the user typed in
     */
    public DukeEmptyInputException(String command) {
        super(EMPTY_INPUT_PREFIX_STRING + command + EMPTY_INPUT_SUFFIX_STRING);
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

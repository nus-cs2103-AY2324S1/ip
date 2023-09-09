package com.ducky.command;

import com.ducky.logic.DuckyException;

/**
 * An exception thrown when a command is not in the correct format.
 */
public class DuckyInvalidCommandFormatException extends DuckyException {

    /**
     * Constructs an invalid format exception that contains a preset message
     * and appends a specified custom message.
     * @param message Custom error information to be appended to the error message.
     */
    public DuckyInvalidCommandFormatException(String message) {
        super(String.format("Um... there's something wrong with your command :(\n%s", message));
    }
}

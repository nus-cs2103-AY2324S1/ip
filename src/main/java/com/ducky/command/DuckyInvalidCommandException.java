package com.ducky.command;

import com.ducky.common.DuckyException;

/**
 * An exception thrown when input that cannot be recognised as a command is given.
 */
public class DuckyInvalidCommandException extends DuckyException {

    private static final String INVALID_COMMAND_ERROR_MSG = "Um... Sorry, I don't know what that means.";

    /**
     * Constructs a new invalid command exception with a preset message.
     */
    public DuckyInvalidCommandException() {
        super(INVALID_COMMAND_ERROR_MSG);
    }
}

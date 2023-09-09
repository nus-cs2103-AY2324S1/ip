package com.ducky.command;

import com.ducky.logic.DuckyException;

/**
 * An exception thrown when input that cannot be recognised as a command is given.
 */
public class DuckyInvalidCommandException extends DuckyException {

    /**
     * Constructs a new invalid command exception with a preset message.
     */
    public DuckyInvalidCommandException() {
        super("Um... Sorry, I don't know what that means.");
    }
}

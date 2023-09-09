package com.ducky.logic;

/**
 * An exception thrown by Ducky.
 */
public class DuckyException extends Exception {

    /**
     * Constructs a new exception with the specified message.
     * @param message Message to be included in exception.
     */
    public DuckyException(String message) {
        super(message);
    }
}

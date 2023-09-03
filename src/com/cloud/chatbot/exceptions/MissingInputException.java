package com.cloud.chatbot.exceptions;



/**
 * Thrown when the user does not specify all required inputs.
 */
public class MissingInputException extends Exception {
    public MissingInputException() {}

    public MissingInputException(String message) {
        super(message);
    }
}

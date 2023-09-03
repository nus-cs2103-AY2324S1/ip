package com.cloud.chatbot.exceptions;



/**
 * An exception for when the user does not specify all required inputs.
 */
public class MissingInputException extends Exception {
    public MissingInputException() {}

    public MissingInputException(String message) {
        super(message);
    }
}

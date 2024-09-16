package com.alpha.exceptions;

/**
 * The type Invalid date time exception.
 */
public class InvalidDateTimeException extends Exception {
    /**
     * Instantiates a new Invalid date time exception.
     *
     * @param message the message
     */
    public InvalidDateTimeException(String message) {
        super(message);
    }

    /**
     * The type Invalid date time format exception.
     */
    public static class InvalidDateTimeFormatException extends InvalidDateTimeException {
        /**
         * Instantiates a new Invalid date time format exception.
         */
        public InvalidDateTimeFormatException() {
            super("Please ensure date time is in the following format: yyyy-MM-dd HH:mm");
        }
    }
}

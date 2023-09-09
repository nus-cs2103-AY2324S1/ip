package com.alpha.exceptions;

/**
 * The type Invalid task exception.
 */
public class InvalidTaskException extends Exception {

    /**
     * Instantiates a new Invalid task exception.
     *
     * @param message The exception message.
     */
    public InvalidTaskException(String message) {
        super(message);
    }

    /**
     * The type Invalid to do exception.
     */
    public static class InvalidToDoException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid to do exception.
         *
         * @param message The exception message.
         */
        public InvalidToDoException(String message) {
            super(message
                    + "Please ensure that the format is as follows:\n "
                    + "todo <task name>");
        }
    }

    /**
     * The type Invalid deadline exception.
     */
    public static class InvalidDeadlineException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid deadline exception.
         *
         * @param message The exception message.
         */
        public InvalidDeadlineException(String message) {
            super(message
                    + "Please ensure that the format is as follows:\n "
                    + "deadline <task name> /by <yyyy-MM-dd HH:mm>");
        }
    }

    /**
     * The type Invalid event exception.
     */
    public static class InvalidEventException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid event exception.
         *
         * @param message The exception message.
         */
        public InvalidEventException(String message) {
            super(message
                    + "Please ensure that the format is as follows:\n "
                    + "event <task name> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
        }
    }
}

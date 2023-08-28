package com.alpha.exceptions;

/**
 * The type Invalid task exception.
 */
public class InvalidTaskException extends Exception {

    /**
     * Instantiates a new Invalid task exception.
     *
     * @param message the message
     */
    public InvalidTaskException(String message) {
        super(message);
    }

    /**
     * The type Invalid command exception.
     */
    public static class InvalidCommandException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid command exception.
         */
        public InvalidCommandException() {
            super("Command given is in an invalid format. "
                    + "Please ensure that the format is as follows:\n "
                    + "<command> <task name> <additional inputs>");
        }
    }

    /**
     * The type Invalid to do exception.
     */
    public static class InvalidToDoException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid to do exception.
         */
        public InvalidToDoException() {
            super(
                    "ToDo is in an invalid format. "
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
         */
        public InvalidDeadlineException() {
            super(
                    "Deadline is in an invalid format. "
                            + "Please ensure that the format is as follows:\n "
                            + "deadline <task name> /by <datetime>");
        }
    }

    /**
     * The type Invalid event exception.
     */
    public static class InvalidEventException extends InvalidTaskException {

        /**
         * Instantiates a new Invalid event exception.
         */
        public InvalidEventException() {
            super(
                    "Event is in an invalid format. "
                            + "Please ensure that the format is as follows:\n "
                            + "event <task name> /from <datetime> /to <datetime>");
        }
    }
}

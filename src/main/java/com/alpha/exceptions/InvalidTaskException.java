package com.alpha.exceptions;

public class InvalidTaskException extends Exception {

    public InvalidTaskException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends InvalidTaskException {

        public InvalidCommandException() {
            super("Command given is in an invalid format. "
                    + "Please ensure that the format is as follows:\n "
                    + "<command> <task name> <additional inputs>");
        }
    }

    public static class InvalidToDoException extends InvalidTaskException {

        public InvalidToDoException() {
            super(
                    "ToDo is in an invalid format. "
                            + "Please ensure that the format is as follows:\n "
                            + "todo <task name>");
        }
    }

    public static class InvalidDeadlineException extends InvalidTaskException {

        public InvalidDeadlineException() {
            super(
                    "Deadline is in an invalid format. "
                            + "Please ensure that the format is as follows:\n "
                            + "deadline <task name> /by <datetime>");
        }
    }

    public static class InvalidEventException extends InvalidTaskException {

        public InvalidEventException() {
            super(
                    "Event is in an invalid format. "
                            + "Please ensure that the format is as follows:\n "
                            + "event <task name> /from <datetime> /to <datetime>");
        }
    }

    public static class UnknownTaskException extends InvalidTaskException {

        public UnknownTaskException() {
            super("Please enter a valid task: todo, deadline, event");
        }
    }

}

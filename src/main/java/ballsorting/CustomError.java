package ballsorting;

/**
 * Class containing custom exceptions that handle invalid user inputs.
 */
public class CustomError extends Exception {
    public CustomError(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Thrown when there is no search term input by the user.
     */
    public static class emptySearchTermException extends CustomError {
        public emptySearchTermException() {
            super(CustomErrorHandling.emptySearchTerm());
        }
    }

    /**
     * Thrown when there is no todo description entered by the user.
     */
    public static class emptyTodoDescriptionException extends CustomError {
        public emptyTodoDescriptionException() {
            super(CustomErrorHandling.emptyTodoDescription());
        }
    }

    /**
     * Thrown when there is no deadline description entered by the user.
     */
    public static class emptyDeadlineDescriptionException extends CustomError {
        public emptyDeadlineDescriptionException() {
            super(CustomErrorHandling.emptyDeadlineDescription());
        }
    }

    /**
     * Thrown when there is no deadline end DateTime entered by the user.
     */
    public static class emptyDeadlineDuedateException extends CustomError {
        public emptyDeadlineDuedateException() {
            super(CustomErrorHandling.emptyDeadlineDuedate());
        }
    }

    /**
     * Thrown when there is no event description entered by the user.
     */
    public static class emptyEventDescriptionException extends CustomError {
        public emptyEventDescriptionException() {
            super(CustomErrorHandling.emptyEventDescription());
        }
    }

    /**
     * Thrown when there is no event start DateTime entered by the user.
     */
    public static class emptyEventStartDateException extends CustomError {
        public emptyEventStartDateException() {
            super(CustomErrorHandling.emptyEventStartDate());
        }
    }

    /**
     * Thrown when there is no event end DateTime entered by the user.
     */
    public static class emptyEventEndDateException extends CustomError {
        public emptyEventEndDateException() {
            super(CustomErrorHandling.emptyEventEndDate());
        }
    }

    /**
     * Thrown when the event end DateTime entered is before the event start DateTime entered.
     */
    public static class invalidEventDatesException extends CustomError {
        public invalidEventDatesException() {
            super(CustomErrorHandling.invalidEventDates());
        }
    }

    /**
     * Thrown when the task description is the same as a task that is already present in the tasklist.
     */
    public static class duplicatedTaskException extends CustomError {
        public duplicatedTaskException() {
            super(CustomErrorHandling.duplicatedTask());
        }
    }

    /**
     * Thrown when the user enters an invalid command.
     */
    public static class commandNotFoundException extends CustomError {
        public commandNotFoundException() {
            super(CustomErrorHandling.commandNotFound());
        }
    }
}

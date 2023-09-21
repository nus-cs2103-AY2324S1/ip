package ballsorting;

public class CustomError extends Exception {
    public CustomError(String errorMessage) {
        super(errorMessage);
    }
    public static class emptySearchTermException extends CustomError {
        public emptySearchTermException() {
            super(CustomErrorHandling.emptySearchTerm());
        }
    }
    public static class emptyTodoDescriptionException extends CustomError {
        public emptyTodoDescriptionException() {
            super(CustomErrorHandling.emptyTodoDescription());
        }
    }
    public static class emptyDeadlineDescriptionException extends CustomError {
        public emptyDeadlineDescriptionException() {
            super(CustomErrorHandling.emptyDeadlineDescription());
        }
    }
    public static class emptyDeadlineDuedateException extends CustomError {
        public emptyDeadlineDuedateException() {
            super(CustomErrorHandling.emptyDeadlineDuedate());
        }
    }
    public static class emptyEventDescriptionException extends CustomError {
        public emptyEventDescriptionException() {
            super(CustomErrorHandling.emptyEventDescription());
        }
    }
    public static class emptyEventStartDateException extends CustomError {
        public emptyEventStartDateException() {
            super(CustomErrorHandling.emptyEventStartDate());
        }
    }
    public static class emptyEventEndDateException extends CustomError {
        public emptyEventEndDateException() {
            super(CustomErrorHandling.emptyEventEndDate());
        }
    }
    public static class invalidEventDatesException extends CustomError {
        public invalidEventDatesException() {
            super(CustomErrorHandling.invalidEventDates());
        }
    }
    public static class duplicatedTaskException extends CustomError {
        public duplicatedTaskException() {
            super(CustomErrorHandling.duplicatedTask());
        }
    }
    public static class commandNotFoundException extends CustomError {
        public commandNotFoundException() {
            super(CustomErrorHandling.commandNotFound());
        }
    }
}

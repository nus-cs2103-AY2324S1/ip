package duke;
public class MYBotExceptions extends Exception {

    public MYBotExceptions(String message) {
        super(message);
    }

    public static class EmptyDetailsException extends MYBotExceptions {
        public EmptyDetailsException(String description, String taskType) {
            super("☹ OOPS! The " + description + " of the " + taskType + " cannot be empty.");
        }
    }

    public static class InvalidInputException extends MYBotExceptions {
        public InvalidInputException(String description, String taskType) {
            super("☹ OOPS! The " + description + " must have a " + taskType + ".");
        }
    }

    public static class UnknownCommandException extends MYBotExceptions {
        public UnknownCommandException() {
            super("☹ OOPS! I'm sorry, but I don't know what that means :(");
        }
    }

    public static class InvalidTaskException extends MYBotExceptions {
        public InvalidTaskException() {
            super("☹ OOPS! This is an invalid task :(");
        }
    }

    public static class NoSuchTaskException extends MYBotExceptions {
        public NoSuchTaskException() {
            super("☹ Oops! Task not found:(");
        }
    }
}

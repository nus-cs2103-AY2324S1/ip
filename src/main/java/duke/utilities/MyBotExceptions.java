package duke.utilities;
public class MyBotExceptions extends Exception {

    public MyBotExceptions(String message) {
        super(message);
    }

    public static class EmptyDetailsException extends MyBotExceptions {
        public EmptyDetailsException(String description, String taskType) {
            super("☹ OOPS! The " + description + " of the " + taskType + " cannot be empty.");
        }
    }

    public static class InvalidInputException extends MyBotExceptions {
        public InvalidInputException(String description, String taskType) {
            super("☹ OOPS! The " + description + " must have a " + taskType + ".");
        }
    }

    public static class UnknownCommandException extends MyBotExceptions {
        public UnknownCommandException() {
            super("☹ OOPS! I'm sorry, but I don't know what that means :(");
        }
    }

    public static class InvalidTaskException extends MyBotExceptions {
        public InvalidTaskException() {
            super("☹ OOPS! This is an invalid task :(");
        }
    }

    public static class NoSuchTaskException extends MyBotExceptions {
        public NoSuchTaskException() {
            super("☹ Oops! Task not found:(");
        }
    }
}

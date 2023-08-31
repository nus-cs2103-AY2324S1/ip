public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Invalid command entered. See 'help' for a list of commands you can enter");
    }
}

class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Invalid Task Id entered, see 'list' for the list of tasks");
    }
}

class NoDescriptionException extends DukeException {
    public NoDescriptionException() {
        super("No description found. Please enter a description after your command.");
    }
}

class NoTaskFoundException extends DukeException {
    public NoTaskFoundException() {
        super("No Task Id found. Please enter a Task Id after your command");
    }
}

class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("Invalid Date format. Here are some example dates:\n"
                + "6/3/2023, 16/12/2024");
    }
}

class InvalidDateTimeFormatException extends DukeException {
    public InvalidDateTimeFormatException() {
        super("Invalid DateTime format. Here are some example dates:\n"
                + "6/3/2023 5:30 AM, 16/12/2024 6:30PM");
    }
}

class FileNotLoadedException extends DukeException {
    public FileNotLoadedException() {
        super("No file data. Creating data file and starting new task list...");
    }
}
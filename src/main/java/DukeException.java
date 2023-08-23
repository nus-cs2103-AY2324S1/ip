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
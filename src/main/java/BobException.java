public class BobException extends Exception {
    public String message = "Error.";
}

class WrongInputException extends BobException {
    public String message = "Oh no, I don't understand that...";
}

class MissingTaskException extends BobException {
    public String message = "Task description is missing!";
}

class MissingDeadlineException extends BobException {
    public String message = "A due date must be given for deadlines!";
}

class MissingEventDatesException extends BobException {
    public String message = "Start and end times must be provided for events!";
}

class MissingIndexException extends BobException {
    public String message = "Task index must be provided!";
}

public class DukeException extends Exception {
    public String message = "Error.";
}

class WrongInputException extends DukeException {
    public String message = "Oh no, I don't understand that...";
}

class MissingTaskException extends DukeException {
    public String message = "Task description is missing!";
}

class MissingDeadlineException extends DukeException {
    public String message = "A due date must be given for deadlines!";
}

class MissingEventDatesException extends DukeException {
    public String message = "Start and end times must be provided for events!";
}

class MissingIndexException extends DukeException {
    public String message = "Task index must be provided!";
}

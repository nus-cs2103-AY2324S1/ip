public class InvalidDescriptionException extends DukeException {

    InvalidDescriptionException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
    }
}

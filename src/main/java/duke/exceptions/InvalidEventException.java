package duke.exceptions;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Please enter an event with the format '<<message>> /from dd/mm/yyyy HH:mm /to dd/mm/yyyy HH:mm' format");
    }
}

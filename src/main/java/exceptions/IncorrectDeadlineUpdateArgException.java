package exceptions;

/**
 * IncorrectDeadlineUpdateArgException is used when a deadline task to be edited has invalid arguments.
 *
 * @author Sebastian Tay
 */
public class IncorrectDeadlineUpdateArgException extends IncorrectInputException {
    public IncorrectDeadlineUpdateArgException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct Usage: update {index} {description} /by {dd/MM/yyyy HHmm}";
    }
}

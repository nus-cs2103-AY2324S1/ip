package exceptions;

/**
 * IncorrectToDoUpdateArgException is used when the task to be edited has invalid arguments.
 *i
 * @author Sebastian Tay
 */
public class IncorrectToDoUpdateArgException extends IncorrectInputException {
    public IncorrectToDoUpdateArgException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct usage: update {index} {description} ";
    }
}

package exceptions;

/**
 * IncorrectToDoInputException is used when the user inputs invalid argument while trying to add a task.
 *
 * @author Sebastian Tay
 */
public class IncorrectToDoInputException extends IncorrectInputException {
    public IncorrectToDoInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct usage: todo {description}";
    }
}

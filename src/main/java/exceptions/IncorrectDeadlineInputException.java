package exceptions;

/**
 * IncorrectDeadlineInputException is used when the user inputs invalid argument while trying to add a deadline.
 *
 * @author Sebastian Tay
 */
public class IncorrectDeadlineInputException extends IncorrectInputException {
    public  IncorrectDeadlineInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Correct usage: deadline {description} /by {dd/MM/yyyy HHmm}";
    }
}

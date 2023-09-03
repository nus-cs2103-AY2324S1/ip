package vedaexceptions;

/**
 * IncorrectInputException is a form of RuntimeException that is thrown when the user provide an invalid input.
 *
 * @author Sebastian Tay
 */
public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Please ensure that you key in your arguments correctly.";
    }
}

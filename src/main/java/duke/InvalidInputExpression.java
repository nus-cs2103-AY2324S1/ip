package duke;

/**
 * Custom class to handle invalid inout exception
 */
public class InvalidInputExpression extends Exception {
    public InvalidInputExpression(String input) {
        super(input);
    }
}

package exceptions;

/**
 * An Exception to be thrown when the task description is not provided.
 *
 * @author Anton Tan Hong Zhi
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Returns the string representation of the Exception
     * @return a String represnting the Exception
     */
    public String toString() {
        return "Description cannot be empty";
    }
}

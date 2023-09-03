package taskmate.exceptions;

/**
 * The InvalidToException class is a child class of the Exception class. It is thrown when the user formats their
 * `event` command incorrectly, not writing the "to" clause of the `event` command in the "YYYY-mm-dd" format.
 * This exception is not thrown when the user leaves the "to" clause empty. In such a case, an EmptyToException is
 * thrown instead.
 */
public class InvalidToException extends Exception {
}

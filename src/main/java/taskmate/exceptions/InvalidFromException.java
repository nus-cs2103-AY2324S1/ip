package taskmate.exceptions;

/**
 * The InvalidFromException class is a child class of the Exception class. It is thrown when the user formats their
 * `event` command incorrectly, not writing the "from" clause of the `event` command in the "YYYY-mm-dd" format.
 * This exception is not thrown when the user leaves the "from" clause empty. In such a case, an EmptyFromException is
 * thrown instead.
 */
public class InvalidFromException extends Exception {
}

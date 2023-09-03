package taskmate.exceptions;

/**
 * The InvalidByException class is a child class of the Exception class. It is thrown when the user formats their
 * `deadline` command incorrectly, not writing the "by" clause of the `deadline` command in the "YYYY-mm-dd" format.
 * This exception is not thrown when the user leaves the "by" clause empty. In such a case, an EmptyByException is
 * thrown instead.
 */
public class InvalidByException extends Exception {
}

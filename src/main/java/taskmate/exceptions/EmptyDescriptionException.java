package taskmate.exceptions;

/**
 * The EmptyDescriptionException class is a child class of the InvalidDescriptionException class. It is thrown when the
 * user formats their task commands incorrectly, leaving the "name" clause of the command empty. The task commands
 * relevant to this EmptyDescriptionException class are the `todo`, `deadline`, and `event` commands.
 */
public class EmptyDescriptionException extends InvalidDescriptionException {
}

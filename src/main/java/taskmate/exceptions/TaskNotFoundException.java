package taskmate.exceptions;

/**
 * The TaskNotFoundException class is a child class of the Exception class. It is thrown when the user inputs their
 * `mark`, `unmark`, or `delete` commands incorrectly, specifying the "markIndex", "unmarkIndex", or "deleteIndex"
 * clauses with an integer that is outside the range of task indexes.
 * For example, if there are n tasks in the task list and the user specifies an integer smaller than 1 or greater
 * than n, this exception will be thrown.
 */
public class TaskNotFoundException extends Exception {
}

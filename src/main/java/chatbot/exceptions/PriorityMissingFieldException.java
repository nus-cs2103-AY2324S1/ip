package chatbot.exceptions;

/**
 * Class that represents an exception due to incorrect priority command provided by user.
 */
public class PriorityMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to assign a priority to a task,\n"
                + "use priority <index> <priority>.\n"
                + "The index cannot be empty, and priority must be one of {H, M, L}.";
    }
}

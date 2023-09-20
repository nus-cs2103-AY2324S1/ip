package chatbot.exceptions;

/**
 * Class that represents an exception due to incorrect find command provided by user.
 */
public class FindMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to find a task, use find <Description>\n"
                + "The description cannot be empty.";
    }
}

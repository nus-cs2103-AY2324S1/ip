package chatbot.exceptions;

/**
 * Class that represents an exception due to incorrect mark or unmark command provided by user.
 */
public class MarkMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to mark / unmark a task, use mark <index> / unmark <index>\n"
                + "The index cannot be empty, \nand there shouldn't be any more field after the index";
    }
}

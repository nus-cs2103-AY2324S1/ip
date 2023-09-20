package chatbot.exceptions;

/**
 * Class that represents an exception due to incorrect delete command provided by user.
 */
public class DeleteMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to delete a task, use delete <index>\n"
                + "The index cannot be empty, \nand there shouldn't be any more field after the index";
    }
}

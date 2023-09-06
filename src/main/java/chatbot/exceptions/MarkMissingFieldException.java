package chatbot.exceptions;

public class MarkMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to mark / unmark a task, use mark <index> / unmark <index>\n"
                + "The index cannot be empty, and there shouldn't be any more field after the index";
    }
}

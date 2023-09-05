package chatbot.exceptions;

public class DeleteMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "\tOOPS, to delete a task, use delete<index>\n"
                + "\tThe index cannot be empty, and there shouldn't be any more field after the index";
    }
}

package chatbot.exceptions;

public class DeadlineMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "\tOOPS, to create a deadline task, use deadline <Description> /by <Deadline>\n"
                + "\tThe description, /by handle and deadline cannot be empty";
    }
}

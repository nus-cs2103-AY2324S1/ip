package chatbot.exceptions;

public class DeadlineMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to create a deadline task, use deadline <Description> /by <Deadline>\n"
                + "The description, /by handle and deadline cannot be empty";
    }
}

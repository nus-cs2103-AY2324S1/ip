package chatbot.exceptions;

public class EventMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "\tOOPS, to create an event task, use event <Description> /from <from> /to <to>\n"
                + "\tThe description, /from handle, from, /to handle, and to cannot be empty";
    }
}

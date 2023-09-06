package chatbot.exceptions;

public class EventMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to create an event task, use event <Description> /from <from> /to <to>\n"
                + "The description, /from handle, from, /to handle, and to cannot be empty";
    }
}

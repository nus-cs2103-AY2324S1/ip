package chatbot.exceptions;

/**
 * Class that represents an exception due to incorrect event command provided by user.
 */
public class EventMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to create an event task, \nuse event <Description> /from <from> /to <to>\n"
                + "The description, /from handle, from, \n/to handle, and to cannot be empty";
    }
}

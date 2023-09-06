package chatbot.exceptions;

public class TodoMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "OOPS, to create a todo task, use todo <Description>\n"
                + "The description cannot be empty";
    }
}

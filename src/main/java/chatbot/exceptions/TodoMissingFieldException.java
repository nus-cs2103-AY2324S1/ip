package chatbot.exceptions;

public class TodoMissingFieldException extends MissingFieldException {
    @Override
    public String toString() {
        return "\tOOPS, to create a todo task, use todo <Description>\n"
                + "\tThe description cannot be empty";
    }
}

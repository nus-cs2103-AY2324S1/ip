package teho.exceptions;

public class EmptyToDoDescriptionException extends TehOException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}

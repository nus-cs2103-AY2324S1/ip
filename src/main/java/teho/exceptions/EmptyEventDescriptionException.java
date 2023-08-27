package teho.exceptions;

public class EmptyEventDescriptionException extends TehOException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a event cannot be empty.";
    }
}

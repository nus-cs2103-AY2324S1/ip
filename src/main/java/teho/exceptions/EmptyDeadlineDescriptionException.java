package teho.exceptions;

public class EmptyDeadlineDescriptionException extends TehOException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}


package teho.exceptions;

public class EmptyFindDescriptionException extends TehOException {
    @Override
    public String toString() {
        return "☹ OOPS!!! You have not listed what you want to find.";
    }
}

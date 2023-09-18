package teho.exceptions;

public class EmptyFindDescriptionException extends TehOException {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "Ohno! You have not listed what you want to find.";
    }
}

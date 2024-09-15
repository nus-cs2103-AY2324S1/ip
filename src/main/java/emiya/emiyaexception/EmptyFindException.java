package emiya.emiyaexception;

/**
 * An exception that is thrown when the find command is used by the user without providing a word to find.
 */
public class EmptyFindException extends EmiyaException {
    public EmptyFindException() {
        super("Oh no! Find tasks cannot be empty! Please try again!\n");
    }

}

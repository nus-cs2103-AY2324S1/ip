package emiya.emiyaexception;

/**
 * An exception that is thrown when the user uses a todo command but does not input in task details.
 */
public class EmptyTodoException extends EmiyaException{
    public EmptyTodoException() {
        super("-----------------------------------------\n" +
                "Oh no! Todo tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}

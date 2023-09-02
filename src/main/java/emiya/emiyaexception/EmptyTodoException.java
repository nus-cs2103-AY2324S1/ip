package emiya.emiyaexception;

public class EmptyTodoException extends EmiyaException {
    public EmptyTodoException() {
        super("-----------------------------------------\n"
                + "Oh no! Todo tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}

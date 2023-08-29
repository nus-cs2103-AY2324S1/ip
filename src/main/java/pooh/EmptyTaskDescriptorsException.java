package pooh;

public class EmptyTaskDescriptorsException extends PoohException {
    @Override
    public String toString() {
        return "      ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}

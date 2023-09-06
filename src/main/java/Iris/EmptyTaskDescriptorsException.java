package Iris;

public class EmptyTaskDescriptorsException extends IrisException {

    @Override
    public String toString() {
        return "The description of a todo cannot be empty.";
    }
}
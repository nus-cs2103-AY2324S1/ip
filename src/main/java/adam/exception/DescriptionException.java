package adam.exception;

/**
 * This exception is for when a add command is called without a description for the task.
 */
public class DescriptionException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add a description to these adam.tasks";
    }
}

package adam.exception;

public class NoDescriptionException extends AdamException {
    public String getInfo() {
        return "OOPS!!! You need to add a description to these adam.tasks";
    }
}

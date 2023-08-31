package adam.exception;


public class DescriptionException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add a description to these adam.tasks";
    }
}

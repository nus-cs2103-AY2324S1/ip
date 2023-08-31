package adam.exception;

public class OneWordException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! Type in the first word you just entered";
    }
}

package adam.exception;

public class NumberException extends AdamException {
    public String getInfo() {
        return "OOPS!!! You need to follow this adam.command by a number";
    }
}

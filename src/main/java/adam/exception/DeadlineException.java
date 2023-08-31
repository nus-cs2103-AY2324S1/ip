package adam.exception;
public class DeadlineException extends AdamException {
    public String getInfo() {
        return "OOPS!!! You need to add one /by adam.command to indicate by when deadline is";
    }
}

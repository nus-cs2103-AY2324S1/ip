package adam.exception;

public class EventException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add one /from and one /to adam.command";
    }
}

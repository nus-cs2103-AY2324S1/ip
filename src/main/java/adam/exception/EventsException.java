package adam.exception;

public class EventsException extends AdamException {
    public String getInfo() {
        return "OOPS!!! You need to add one /from and one /to adam.command";
    }
}

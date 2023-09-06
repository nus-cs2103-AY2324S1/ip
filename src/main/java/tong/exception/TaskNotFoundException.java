package tong.exception;
public class TaskNotFoundException extends IndexOutOfBoundsException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

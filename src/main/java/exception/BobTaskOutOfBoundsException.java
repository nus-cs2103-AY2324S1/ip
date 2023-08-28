package exception;

public class BobTaskOutOfBoundsException extends BobException {
    public BobTaskOutOfBoundsException() {
        super("Index chosen for task is out of bounds");
    }
}

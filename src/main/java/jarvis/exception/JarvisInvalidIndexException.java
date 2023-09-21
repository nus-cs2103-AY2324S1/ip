package jarvis.exception;

public class JarvisInvalidIndexException extends JarvisException {
    public JarvisInvalidIndexException(int taskIndex) {
        super("OOPS!!! No such task with index " + taskIndex);
    }
}

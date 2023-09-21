package iris;

public class InvalidTaskException extends IrisException {
    @Override
    public String toString() {
        return " No such task! Please reselect carefully!";
    }
}

package iris;

public class NotTimeSensitiveTaskException extends InvalidTaskException{
    @Override
    public String toString() {
        return "Task is not time-sensitive!";
    }
}

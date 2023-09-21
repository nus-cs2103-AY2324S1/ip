package iris;

public class LoadTaskException extends IrisException {
    @Override
    public String toString() {
        return "An error has occurred whilst loading tasks from file.";
    }
}

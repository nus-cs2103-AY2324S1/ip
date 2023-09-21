package iris;

public class WriteTaskException extends IrisException {
    @Override
    public String toString() {
        return "An error has occurred whilst writing to file.";
    }
}

public class PogoEmptyTaskException extends PogoException {
    public PogoEmptyTaskException() {
        super("Task description cannot be empty");
    }
}

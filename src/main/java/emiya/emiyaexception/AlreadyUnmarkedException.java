package emiya.emiyaexception;

public class AlreadyUnmarkedException extends EmiyaException {
    public AlreadyUnmarkedException() {
        super("This task is already unmarked!");
    }
}

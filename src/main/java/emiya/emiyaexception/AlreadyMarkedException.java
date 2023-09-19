package emiya.emiyaexception;

public class AlreadyMarkedException extends EmiyaException {
    public AlreadyMarkedException() {
        super("This task is already marked!");
    }
}

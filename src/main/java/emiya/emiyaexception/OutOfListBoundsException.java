package emiya.emiyaexception;

public class OutOfListBoundsException extends EmiyaException {
    public OutOfListBoundsException() {
        super("-----------------------------------------\n"
                + "Task does not exist! Please try with a different value\n"
                + "-----------------------------------------\n");
    }
}

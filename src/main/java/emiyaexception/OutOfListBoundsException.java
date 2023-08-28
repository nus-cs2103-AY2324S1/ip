package emiyaexception;

public class OutOfListBoundsException extends EmiyaException{
    public OutOfListBoundsException() {
        super("-----------------------------------------\n" +
                "task.Task does not exist! Please try with a different value\n"
                + "-----------------------------------------\n");
    }
}

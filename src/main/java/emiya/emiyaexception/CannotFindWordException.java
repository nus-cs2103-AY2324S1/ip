package emiya.emiyaexception;

public class CannotFindWordException extends EmiyaException{
    public CannotFindWordException() {
        super("-----------------------------------------\n" +
                "Cannot find word given in task list!\n"
                + "-----------------------------------------\n");
    }
}

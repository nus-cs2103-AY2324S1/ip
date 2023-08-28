package emiyaexception;

public class EmptyDeadlineException extends EmiyaException{
    public EmptyDeadlineException() {
        super("-----------------------------------------\n" +
                "Oh no! task.Deadline tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}

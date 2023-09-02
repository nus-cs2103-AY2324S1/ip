package emiya.emiyaexception;

public class InvalidDateException extends EmiyaException {
    public InvalidDateException() {
        super("-----------------------------------------\n"
                + "Please enter a valid date!\n"
                + "-----------------------------------------\n");
    }
}

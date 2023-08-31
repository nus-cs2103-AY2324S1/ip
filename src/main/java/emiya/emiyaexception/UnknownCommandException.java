package emiya.emiyaexception;

public class UnknownCommandException extends EmiyaException{
    public UnknownCommandException() {
        super("-----------------------------------------\n" +
                "Unknown command received! Please try again!\n"
                + "-----------------------------------------\n");
    }
}

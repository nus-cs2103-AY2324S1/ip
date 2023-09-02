package emiya.emiyaexception;

public class NoToException extends EmiyaException {
    public NoToException() {
        super("-----------------------------------------\n"
                + "It seems like there's an error in your input! Did you remember to use /to in your input?\n"
                + "-----------------------------------------\n");
    }
}

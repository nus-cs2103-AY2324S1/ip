package emiya.emiyaexception;

public class NoByException extends EmiyaException{
    public NoByException() {
        super("-----------------------------------------\n" +
                "It seems like there's an error in your input! Did you remember to use /by in your input?\n"
                + "-----------------------------------------\n");
    }
}


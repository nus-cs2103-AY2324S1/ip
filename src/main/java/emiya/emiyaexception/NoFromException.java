package emiya.emiyaexception;

public class NoFromException extends EmiyaException{
    public NoFromException() {
        super("-----------------------------------------\n" +
                "It seems like there's an error in your input! Did you remember to use /from in your input?\n"
                + "-----------------------------------------\n");
    }
}
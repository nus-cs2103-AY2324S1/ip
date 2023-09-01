package emiya.emiyaexception;

public class EmptyMarkException extends EmiyaException{
    public EmptyMarkException() {
        super("-----------------------------------------\n" +
                "Please give a list index for mark operations!\n"
                + "-----------------------------------------\n");
    }
}

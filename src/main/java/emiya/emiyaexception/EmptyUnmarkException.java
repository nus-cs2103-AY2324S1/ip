package emiya.emiyaexception;

public class EmptyUnmarkException extends EmiyaException{
    public EmptyUnmarkException() {
        super("-----------------------------------------\n" +
                "Please give a list index for unmark operations!\n"
                + "-----------------------------------------\n");
    }
}
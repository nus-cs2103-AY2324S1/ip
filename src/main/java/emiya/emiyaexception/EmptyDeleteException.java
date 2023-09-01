package emiya.emiyaexception;

public class EmptyDeleteException extends EmiyaException{
    public EmptyDeleteException() {
        super("-----------------------------------------\n" +
                "Please give a list index for delete operations!\n"
                + "-----------------------------------------\n");
    }
}
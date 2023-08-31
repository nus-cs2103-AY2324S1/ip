package emiya.emiyaexception;

public class EmptyEventException extends EmiyaException{
    public EmptyEventException() {
        super("-----------------------------------------\n" +
                "Oh no! Event tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}

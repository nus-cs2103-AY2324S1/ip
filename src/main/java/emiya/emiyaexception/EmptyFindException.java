package emiya.emiyaexception;

public class EmptyFindException extends EmiyaException{

    public EmptyFindException() {
        super("-----------------------------------------\n" +
                "Oh no! Find tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }

}

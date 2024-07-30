package duke.exception;

public class EmptyInputException extends Exception{
    public EmptyInputException() {
        super("\n\tAccio error! Description, index or date entered cannot be empty! " +
                "Type 'help' to see the list of valid inputs and the expected formats! ");
    }
}

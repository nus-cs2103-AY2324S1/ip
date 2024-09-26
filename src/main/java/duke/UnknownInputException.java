package duke;
//if the user inputs an unknown input, an error message is returned
public class UnknownInputException extends Exception{
    public UnknownInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

/**
 * Exception for input belong to ToDos does not have name
 */
public class TodoEmptyNameException extends Exception{
    /**
     * The constructor
     */
    public TodoEmptyNameException() {
        super("OOPS! The description of todo cannot be empty");
    }
}
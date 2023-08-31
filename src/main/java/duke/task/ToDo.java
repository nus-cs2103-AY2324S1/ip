package duke.task;
import duke.exception.InvalidToDoException;

/**
 * Class for ToDo
 */
public class ToDo extends Task {
    // Constructor

    /**
     * Constructor of class ToDo
     * @param name the name of the todo
     */
    public ToDo(String name) {
        super(name);
    };
    
    // Method

    public static ToDo create(String message) throws InvalidToDoException {
        try {
            return new ToDo(message.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidToDoException();
        }
    }

    /**
     * Method to return the string representation of todo
     * 
     * @return the string representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Method to return the string format of the to do in the storage
     * 
     * @return the string format of the to do in the storage
     */
    @Override
    public String storeInString() {
        return "T | " + (this.getMark() ? "1 | " : "0 | ") + this.getName();
    }
}
package duke.task;
import duke.exception.InvalidToDoException;

/**
 * Class for ToDo
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    };
    
    /**
     * Creates a new ToDo based on the message
     * 
     * @param message the message to create the new todo
     * @return the new todo
     * @throws InvalidToDoException when the todo command message is invalid
     */
    public static ToDo create(String message) throws InvalidToDoException {
        try {
            return new ToDo(message.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidToDoException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storeInString() {
        return "T | " + (this.getMark() ? "1 | " : "0 | ") + this.getName();
    }
}
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

    /**
     * Method to return the string representation of todo
     * 
     * @return the string representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
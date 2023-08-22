/**
 * This is the Todo class, a child class of Task class
 * @author Selwyn
 */
public class Todo extends Task{
    /**
     * Constructor for a Todo task
     *
     * @param detail
     */
    public Todo(String detail) {
        super(detail);
    }

    /**
     * This method returns the string representation of this todo task
     * @return String representation of this todo task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
package adam.tasks;

import java.io.Serializable;

/**
 * This class is used to create Todos object that holds the description of your todo.
 */
public class ToDo extends Task implements Serializable {

    /**
     * Initializes a todo task.
     *
     * @param text Text that gives the description of the Todo object.
     */
    public ToDo(String text) {
        super(text);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

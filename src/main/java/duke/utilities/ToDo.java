package duke.utilities;

/**
 * Class to declare a Todo task
 */
public class ToDo extends Task {
    /**
     * Creates a new instance of a ToDo task
     *
     * @param name Name of task
     */
    public ToDo(String name) {
        super(name, Type.TODO, "");
    }
}

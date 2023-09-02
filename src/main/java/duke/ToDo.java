package duke;


/**
 * This class represents the to-do task
 *
 */
public class ToDo extends Task {
    /**
     * Same as its parent constructor
     *
     * @param name the name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Default display for to-do objects
     *
     * @return the default display
     */
    public String display() {
        if (done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}

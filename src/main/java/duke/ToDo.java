package duke;

/**
 * A duke.Task object with the "T" label
 */
public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    /**
     * Get the description of the task
     *
     * @return The description of the task
     */
    @Override
    public String toString(){
        return "[T] " + super.toString();
    }

}

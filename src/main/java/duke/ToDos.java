package duke;

/**
 * ToDos
 */
public class ToDos extends Task {

    protected String by;

    /**
     * Constructor to create a new ToDo Task
     * @param description description of the ToDo task
     * @param isDone whether the ToDo task has been completed or not
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return the toString of a ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String updateTime(String description) {
        return "The following task cannot be updated because it does not have a time: \n";
    }
}

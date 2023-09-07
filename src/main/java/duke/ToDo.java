package duke;

/**
 * ToDo class is responsible for creating new ToDo object and handle todo event
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDO class
     *
     * @param task the task description
     */
    public ToDo(String task) {
        super(task);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

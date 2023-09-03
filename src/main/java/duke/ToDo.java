package duke;

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

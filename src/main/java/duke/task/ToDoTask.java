package duke.task;

public class ToDoTask extends Task {


    /**
     * Constructor for duke.task.Task.
     *
     * @param description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor() + String.format("| %s ", "TODO");
    }


    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

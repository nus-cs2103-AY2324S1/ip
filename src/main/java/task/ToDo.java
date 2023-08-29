package task;

public class ToDo extends Task {
    /**
     * Initialize task.Task object with task name and task is not done.
     *
     * @param description type String;
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
    @Override
    public String fileFormat() {
        return ("T|" + super.fileFormat());
    }
}

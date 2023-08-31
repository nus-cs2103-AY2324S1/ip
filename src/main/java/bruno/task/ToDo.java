package bruno.task;

/**
 * The ToDo class is responsible for all tasks of Todo type.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(TaskType.TODO, description);
    }

    @Override public String getString() {
        return "[T]" + super.getString();
    }

    @Override public String getFileString() {
        return "T|" + super.getFileString();
    }
}

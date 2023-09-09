package duke;

/**
 * one of the subclasses of a Task
 */
public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    public static ToDo newToDo(String description) {
        return new ToDo(description);
    }

}

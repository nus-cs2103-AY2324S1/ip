package glub.task;

/**
 * Abstraction of a todo event.
 */
public class ToDo extends Task {
    public ToDo(String task, boolean isDone, String tag) {
        super(task, isDone, tag);
    }
    @Override
    public String toSaveFormat() {
        return "T|" + super.toSaveFormat() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package aj;

/**
 * Todo class for Todo Task.
 */
public class Todo extends Task {
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    Todo(String taskName, boolean isMarked) {
        super(taskName, isMarked);
    }
}

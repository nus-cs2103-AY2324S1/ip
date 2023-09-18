package aj;


/**
 * Todo class for Todo Task.
 */
public class Todo extends Task {

    Todo(String taskName, boolean isMarked) {
        super(taskName, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

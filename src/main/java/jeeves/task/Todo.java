package jeeves.task;

/**
 * The representation of a To.do task
 */
public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public Todo(String desc, boolean status) {
        super(desc, status);
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [T]" + super.toString());
    }
}

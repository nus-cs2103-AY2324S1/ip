package Tasks;

/**
 * Represents a todo task in the Duke application.
 * This class extends the base class Task and represents a simple todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with a title.
     *
     * @param title The title of the todo task.
     */
    public Todo(String title, boolean isMarked) {
        super(title, isMarked);
    }

    /**
     * Generates a string representation of the Todo object.
     *
     * @return A formatted string indicating the status (marked or unmarked) and title of the todo task.
     */
    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[T]" + mark + title;
    }

    @Override
    public String toSave() {
        String res = "T";
        res += (isMarked ? "| 1" : "| 0");
        res += "| " + title;
        return res;
    }
}

/**
 * The T*odo class extends Task
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Todo extends Task{

    /** Constructor for Todo */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

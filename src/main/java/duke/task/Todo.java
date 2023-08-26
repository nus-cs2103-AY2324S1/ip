package duke.task;

/**
 * The T*odo class extends duke.task.Task
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Todo extends Task{

    /** Constructor for duke.task.Todo */
    public Todo(String done, String description) {
        super(description, done);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileRepresentation() {
        return ("T" + " | " + (this.isDone ? "1" : "0") + " | " + this.description + "\n");
    }
}

package seedu.duke.tasks;

/**
 * A child class of duke.Tasks, it represents tasks to complete.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String addDataFormat() {
        return "T " + super.addDataFormat();
    }

}

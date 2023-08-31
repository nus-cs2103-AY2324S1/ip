package bongo.task;

public class Todo extends Task {

    /**
     * A constructor for a Todo.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A constructor for a Todo, specifying whether it is done.
     *
     * @param description Todo description.
     * @param isDone      Todo marked done or undone.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String generateStringForTextFile() {
        String isTaskMarkedDone = this.isDone ? "1" : "0";
        return String.join(" | ", "T", isTaskMarkedDone, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

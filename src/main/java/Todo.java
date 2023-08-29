/**
 * Class to encapsulate the logic of an todo task
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class
     * Stored as TODO,{task description},{1 if marked else 0}
     *
     * @param task - the description of the task created
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructor for the Todo class from storage
     * Stored as TODO,{task description},{1 if marked else 0}
     *
     * @param task      - the description of the task created
     * @param completed - if completed
     */
    public Todo(String task, boolean completed) {
        super(task);
        if (completed) {
            this.toggleCompleted();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * returns the stored form of this tasks
     * Stored as TODO,{task description},{1 if marked else 0}
     *
     * @return TODO,{task description},{1 if marked else 0}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[]{"TODO", this.getTask(), this.isCompleted() ? "1" : "0"});
    }
}

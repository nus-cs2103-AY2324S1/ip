/**
 * Class to encapsulate the logic of an todo task
 */
public class TodoTask extends Task {
    /**
     * Constructor for the TodoTask class
     * Stored as TODO,{task description},{1 if marked else 0}
     *
     * @param task - the description of the task created
     */
    public TodoTask(String task) {
        super(task);
    }

    /**
     * Constructor for the TodoTask class from storage
     * Stored as TODO,{task description},{1 if marked else 0}
     *
     * @param task      - the description of the task created
     * @param completed - if completed
     */
    public TodoTask(String task, boolean completed) {
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

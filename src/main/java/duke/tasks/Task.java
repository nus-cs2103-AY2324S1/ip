<<<<<<< HEAD
package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
=======
package duke.tasks;

public abstract class Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private final String description;
    private boolean isDone;
    private final TaskType type;


    public Task(String description, TaskType type) {
        this.description = description;
        isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
    @Override
    public String toString() {
        return (isDone() ? DONE_FLAG : UNDONE_FLAG) + getDescription();
>>>>>>> branch-Level-7
    }
}


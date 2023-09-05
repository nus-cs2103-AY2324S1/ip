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

    public String getFlag() {
        return this.isDone() ? DONE_FLAG : UNDONE_FLAG;
    }

    public abstract String saveString();

    @Override
    public String toString() {
        return (isDone() ? DONE_FLAG : UNDONE_FLAG) + getDescription();
    }
}


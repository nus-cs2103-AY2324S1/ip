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

    public boolean contains(String pattern) {
        if (pattern == null || this.description == null) {
            return false;
        }

        int patternLength = pattern.length();

        for (int i = 0; i <= this.description.length() - patternLength; i++) {
            if (this.description.regionMatches(true, i, pattern, 0, patternLength)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return (isDone() ? DONE_FLAG : UNDONE_FLAG) + getDescription();
    }
}


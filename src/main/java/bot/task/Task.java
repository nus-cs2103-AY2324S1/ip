package bot.task;

public abstract class Task {
    private static final String COMPLETE = "[X] ";
    private static final String INCOMPLETE = "[ ] ";
    protected static final String UNIQUE_FILE_SEPARATOR = " &##& ";
    private final String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setComplete() {
        this.isDone = true;
    }

    public void setIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns true if pattern is present in task name (description), false otherwise
     *
     * @param pattern String pattern to find
     * @return boolean based on presence of pattern in task name
     */
    public boolean hasPattern(String pattern) {
        return this.name.contains(pattern);
    }

    public String fileWriteFormatted() {
        if (this.isDone) {
            return "true" + Task.UNIQUE_FILE_SEPARATOR + this.name;
        } else {
            return "false" + Task.UNIQUE_FILE_SEPARATOR + this.name;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return Task.COMPLETE + this.name;
        } else {
            return Task.INCOMPLETE + this.name;
        }
    }
}

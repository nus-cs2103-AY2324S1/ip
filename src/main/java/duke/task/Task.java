package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public int getStatusInteger() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String writeFile() {
        return this.getStatusInteger() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns true if the task description contains the string given, false otherwise.
     *
     * @param str String that may be in the task description.
     * @return true if the task description contains the string given, false otherwise.
     */
    public boolean contains(String str) {
        return description.contains(str);
    }
}

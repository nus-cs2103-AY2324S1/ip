package tasks;

public abstract class ShibaTask {
    private final String name;
    private boolean isDone;

    protected ShibaTask(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done, if not already done.
     * @return True if the task was not already done, else false.
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        }
        isDone = true;
        return true;
    }

    /**
     * Marks the task as not done, if already done.
     * @return True if the task was already done, else false.
     */
    public boolean markNotDone() {
        if (!isDone) {
            return false;
        }
        isDone = false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}

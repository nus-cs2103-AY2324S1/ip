package duke;

/**
 * Represents priority of a task.
 */
public enum Priority {
    HIGH(1), NORMAL(0);
    private int val;
    Priority(int val) {
        this.val = val;
    }
    @Override
    public String toString() {
        return Integer.toString(val);
    }

}

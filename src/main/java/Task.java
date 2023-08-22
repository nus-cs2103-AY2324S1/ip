/**
 * A class that defines task objects written by users.
 */
public class Task {
    /** A string that indicates task name. */
    private String name;
    /** A boolean that indicates whether task is completed by user. */
    private boolean isDone;

    /**
     * A constructor of a task object.
     * @param name A string that indicates task name.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * @return the icon status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Updates the status of task.
     */
    public void updateDone() {
        this.isDone = !this.isDone;
    }

    /**
     * @return string representation of task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    /**
     *
     * @param obj The object that is compared against this task.
     * @return true if the tasks' names are equal; otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task t = (Task) obj;
            return this.name == t.name;
        }

        return false;
    }
}

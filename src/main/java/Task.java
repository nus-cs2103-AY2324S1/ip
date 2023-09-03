public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with X
    }

    public boolean mark(int id) {
        if (id == 0) {                  /** to mark unmarked task */
            if (isDone == false) {
                isDone = true;
                return true;
            } else {                        /** if the task is already marked */
                return false;
            }
        } else {                        /** to unmark marked task */
            if (isDone == true) {
                isDone = false;
            } else {                        /** if the task is already unmarked */
                return false;
            }
        }
        return false;
    }

    public String toStringAll() {
        return getStatusIcon() + this;
    }

    @Override
    public String toString() {
        return this.description;
    }


}

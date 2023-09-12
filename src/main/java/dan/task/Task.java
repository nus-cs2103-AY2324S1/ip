package dan.task;

import dan.DanException;

public class Task {
    // fields
    protected String description;
    protected boolean isDone;

    //Methods
    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with V
    }

    public boolean mark(int id) {
        if (id == 1) {                  /* to mark unmarked task */
            if (isDone == false) {
                isDone = true;
            } else {                        /* if the task is already marked */
                throw new DanException("");
            }
        } else {                        /* to unmark marked task */
            if (isDone == true) {
                isDone = false;
                return true;
            } else {                        /* if the task is already unmarked */
                return false;
            }
        }
    }

    // toString
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
    public String saveToString() {
        return this.description + "," + (isDone ? 1 : 0);
    }

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
}

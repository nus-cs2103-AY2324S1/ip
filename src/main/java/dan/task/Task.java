package dan.task;

import dan.exceptions.DanException;

public class Task {
    // fields
    protected String description;
    protected boolean isDone;

    //Methods
    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with V
    }

    public void mark(int id) throws DanException {
        if (id == 1) {                  /* to mark task */
            if (isDone == false) {
                isDone = true;
            } else {                        /* if the task is already marked */
                throw new DanException("Already marked!");
            }
        } else {                        /* to unmark task */
            if (isDone == true) {
                isDone = false;
            } else {                        /* if the task is already unmarked */
                throw new DanException("Already unmarked");
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

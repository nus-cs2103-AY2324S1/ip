package veneto.task;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;

public class Task {
    // fields
    protected String description;
    protected boolean isDone;

    //Methods
    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with V
    }

    public void mark(int id) throws VenetoException {
        if (id == 1) {                  /* to mark task */
            if (isDone == false) {
                isDone = true;
            } else {                        /* if the task is already marked */
                throw new VenetoOperateException("Marked");
            }
        } else {                        /* to unmark task */
            if (isDone == true) {
                isDone = false;
            } else {                        /* if the task is already unmarked */
                throw new VenetoOperateException("Unmarked");
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

    /**
     * @param keyword the target keyword
     * @return if the description of the task contains the keyword
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }
}

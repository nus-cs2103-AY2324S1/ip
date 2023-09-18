package veneto.task;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;

public class Task {
    /* fields */
    protected String description;
    protected boolean isDone;

    /* Constructors */
    /**
     * create a new Task
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * create a new task from storage file
     * @param description the description of the task
     * @param isDone the status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /* Methods */
    /**
     * return the status icon of the task
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with V
    }

    /**
     * mark or unmark the task
     * @param funcId the function id
     * @throws VenetoException if the mark operation is done before
     */
    public void mark(int funcId) throws VenetoException {
        if (funcId == 1) {              /* to mark task */
            if (isDone == false) {
                isDone = true;
            } else {                         /* if the task is already marked */
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
    /**
     * explanation of the task
     * @return return task details
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }

    /**
     * generate texts for storage
     * @return String of the task data
     */
    public String saveToString() {
        return this.description + "," + (isDone ? 1 : 0);
    }
}

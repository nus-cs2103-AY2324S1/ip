public class Task {
    // fields
    protected String description;
    protected boolean isDone;

    //Methods
    public String getStatusIcon() {
        return (isDone ? "[V] " : "[ ] "); // mark done task with V
    }

    public boolean mark(int id) {
        if (id == 0) {                  /* to mark unmarked task */
            if (isDone == false) {
                isDone = true;
                return true;
            } else {                        /* if the task is already marked */
                return false;
            }
        } else {                        /* to unmark marked task */
            if (isDone == true) {
                isDone = false;
            } else {                        /* if the task is already unmarked */
                return false;
            }
        }
        return false;
    }

    // toString
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
}

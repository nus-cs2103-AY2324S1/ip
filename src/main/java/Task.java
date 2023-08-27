/**
 * Class for tasks created.
 */
public class Task {
    private boolean status;
    private String taskContent;

    public Task(String reply) {
        this.status = false;
        this.taskContent = reply;
    }

    /**
     * Marks task status as done.
     */
    public void mark() {
        status = true;
    }

    /**
     * Marks task status as not done.
     */
    public void unmark() {
        status = false;
    }

    @Override
    public String toString() {
        if (status) {
            return String.format("[X]%s", taskContent);
        } else {
            return String.format("[ ]%s", taskContent);
        }
    }


    public String showContent() {
        return taskContent;
    }
}

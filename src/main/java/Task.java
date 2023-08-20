public class Task {
    private boolean status;
    private String taskContent;

    public Task(String reply) {
        this.status = false;
        this.taskContent = reply;
    }

    public void mark() {
        status = true;
    }

    public void unmark() {
        status = false;
    }

    @Override
    public String toString() {
        if (status) {
            return String.format("[X] %s", taskContent);
        } else {
            return String.format("[ ] %s", taskContent);
        }
    }

    public String showContent() {
        return taskContent;
    }
}

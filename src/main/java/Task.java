public class Task {
    String taskName;
    boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }
    public void markDone(int taskNum) {
        this.status = true;
    }
    public void markNotDone(int taskNum) {
        this.status = false;
    }

    public String getName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String statusString() {
        String statusString;
        if (this.status) {
            statusString = "X";
        } else {
            statusString = " ";
        }
        return "[" + statusString + "]";
    }
}

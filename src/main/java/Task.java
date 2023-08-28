public class Task {
    protected String taskName;
    protected boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }
    public void markDone() {
        this.status = true;
    }
    public void unmarkDone() {
        this.status = false;
    }

    public String getName() {
        return this.taskName;
    }

    public String getType() { return "No task type"; };

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

    String getDescription() {
        return toString();
    }

    public String statusAndTask() {
        return statusString() + " " + this.taskName;
    }

    public String toString() {
        return this.taskName;
    }
}

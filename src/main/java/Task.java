public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTask() {
        String output = (isDone ? " [X] " : " [ ] ") + this.taskName;
        return output;
    }

    public String markAsDone() {
        this.isDone = true;
        return "'" + this.taskName + "'" + " is completed! Good job :)";
    }

    public String markAsUndone() {
        this.isDone = false;
        return "'" + this.taskName + "'" + " is now not completed :(";
    }
}

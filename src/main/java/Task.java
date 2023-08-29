public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void doTask() {
        this.isDone = true;
    }

    public void undoTask() {
        this.isDone = false;
    }

    public String getMarkedIcon() {
        String markedIcon = isDone ? "X" : " ";

        return markedIcon;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        String markedIcon = getMarkedIcon();

        return "[" + markedIcon + "] " + this.taskDescription;
    }
}

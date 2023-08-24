class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getTask() {
        return this.description;
    }

    public String taskString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = str + this.description;
        return output;
    }
}
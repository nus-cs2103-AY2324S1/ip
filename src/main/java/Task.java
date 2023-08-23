class Task {
    protected boolean isDone = false;
    protected String name = "";

    public Task(String name) {
        this.name = name;
    }
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unMarkTask() {
        this.isDone = false;
    }
}
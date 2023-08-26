class Task {
    protected String description;
    protected boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    Task(String description) {
        this(description, false);
    }

    String stringToFile() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    void markIsDone() {
        this.isDone = true;
    }

    void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}

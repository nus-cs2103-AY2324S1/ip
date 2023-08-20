abstract class Task {
    private boolean isDone;
    private String taskName;

    protected Task(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) throw new IncompleteDescriptionException();
        isDone = false;
        this.taskName = taskName;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unMarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}

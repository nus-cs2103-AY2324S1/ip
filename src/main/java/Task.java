public abstract class Task {
    protected String taskName;
    private boolean completed;
    protected String oneLetterAbbrev;
    Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    @Override
    public String toString() {
        return this.isCompleted() ? "[" + this.oneLetterAbbrev + "][X] " + this.taskName : "[" + this.oneLetterAbbrev + "][ ] " + this.taskName;
    }
}

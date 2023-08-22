public abstract class Task {
    protected String taskName;
    private boolean completed;
    protected String oneLetterAbbrev;
    Task(Parser input) {
        this.taskName = input.getDefaultString();
        completed = false;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public void mark() {
        this.completed = true;
    }
    public void unmark() {
        this.completed = false;
    }
    @Override
    public String toString() {
        return this.isCompleted() ? "[" + this.oneLetterAbbrev + "][X] " + this.taskName : "[" + this.oneLetterAbbrev + "][ ] " + this.taskName;
    }
}

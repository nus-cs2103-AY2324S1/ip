package duke.data.task;

public abstract class Task {
    protected Description description;
    protected boolean isDone = false;
    protected String userInputString;

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getUserInputString() throws NullPointerException {
        return this.userInputString;
    }

    public void setUserInputString(String userInputString) {
        this.userInputString = userInputString;
    }

    public boolean hasKeyword(String keyword) {
       return this.description.hasKeyword(keyword);
    }
}
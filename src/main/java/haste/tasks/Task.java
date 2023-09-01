package haste.tasks;

public abstract class Task {
    protected boolean isComplete;
    protected String description;

    public Task(String description, boolean isComplete){
        this.description = description;
        this.isComplete = isComplete;
    }

    public void markDone() {
        isComplete = true;
    }

    public void markUndone() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public static boolean checkEmpty(String s) {
        return s == null || s.isBlank();
    }

    public String save() {
       return this.isComplete + "|" + this.description;
    }

}

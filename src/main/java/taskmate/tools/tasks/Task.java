package taskmate.tools.tasks;

public abstract class Task {

    String name;
    boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.name;
    }

    abstract String getTaskType();


    public abstract String formatTaskForSaving();
    // String format to save the task to disk

}

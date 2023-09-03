package tasks;

/**
 * Task is an abstract class encapsulating common methods for other task classes.
 *
 * @author Sebastian Tay
 */
public abstract class Task {
    private boolean isDone;
    private String description;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * updateCompletionStatus will change the current completion status of the task
     * to true or false if it is currently false or true respectively.
     */
    public void updateCompletionStatus() {
        //If done, change to not done or false
        //Else, change to done or true
        this.isDone = !(this.isDone);
    }

    public String getStatusIcon() {
        //Events.Task is marked as done with an "X"
        return isDone
                ? "X"
                : " ";
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public abstract String getType();

    public abstract String convertToStorageForm();
}

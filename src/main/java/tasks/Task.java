package tasks;

/**
 * The parent class that represents the task that the user will input.
 */
public class Task {
    private String type;
    private String name;
    private boolean isMarked;
    private boolean isEmpty = false;

    /**
     * Constructs the method for task.
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }
    public Task() {
        this.isEmpty = true;
    }
    public boolean isItEmpty() {
        return isEmpty;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Marks a task as marked.
     */
    public void markDone() {
        isMarked = true;
    }

    /**
     * UnMarks a task.
     */
    public void unmarkDone() {
        isMarked = false;
    }
    /**
     * Represent the task in String.
     *
     * @return The syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        assert !type.equals(" ") : "Type must be a character related to its task type";
        return "  [" + type + "]" + "[" + (isMarked ? "X" : " ") + "] " + name;
    }
}

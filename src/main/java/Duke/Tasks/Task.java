package Duke.Tasks;

/**
 * The Task class represents a basic task with a description and completion status.
 */
public class Task {
    private Boolean isDone = false;

    private String task;

    public Task(String input) {
        task = input;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Checks if the task matches a given description.
     *
     * @param desc The description to compare.
     * @return True if the task's description matches the given description; otherwise, false.
     */
    public boolean filterMatchDesc(String desc) {
        String[] splitsy = this.toString().split(" \\| ");
        return splitsy[2].trim().equals(desc.trim());
    }


    public void markUndone() {
        isDone = false;
    }

    protected boolean isDone() {
        return isDone;
    }

    public String toString() {
        if (isDone) {
            return "[X] " + "| " + task;
        } else {
            return "[ ] " +"| " + task;
        }
    }

}
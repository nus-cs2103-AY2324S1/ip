package ruiz.task;

public class Task {
    private boolean completed;
    private String description, type;

    /**
     * A constructor for the public Task class.
     *
     * @param description contains the description of the task.
     */
    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    /**
     * Marks the current task.
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Unmarks the current task.
     */
    public void unmark() {
        this.completed = false;
    }

    public String saveTaskString() {
        return completed ? " | 1 | " + description : " | 0 | " + description;
    }
    public Boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * This method converts the value of the task into a String value.
     *
     * @return String representation of the task with its completion status.
     */
    @Override
    public String toString() {
        String mark = "[ ]";
        if (this.completed) {
            mark = "[X]";
        }
        return mark + " " + description;
    }
}

package ruiz.task;

/**
 * Represents a task that has a description and completion check.
 */
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

    /**
     * This method converts the value of the task into a string format that is stored.
     *
     * @return String representation of the task in its storage format.
     */
    public String saveTaskString() {
        return completed ? " | 1 | " + description : " | 0 | " + description;
    }

    /**
     * This method checks if the description of the task has the keyword the user is looking for.
     *
     * @param keyword keyword the user is searching for.
     * @return Boolean representing if the keyword is present.
     */
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

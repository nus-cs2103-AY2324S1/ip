package jarvis.tasks;

import java.time.LocalDateTime;

/**
 * The Task class holds all the properties of Task objects in Jarvis
 * application.
 * Represents a task with a title, completion status, and optional due date.
 */
public class Task {
    private String title;
    private boolean isCompleted;
    private LocalDateTime dueDate;

    public Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Gets the status icon of the task (1 if completed, 0 if uncompleted).
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isCompleted ? "1 | " : "0 | ");
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmarkCompleted() {
        isCompleted = false;
    }

    /**
     * Gets the due date of the task (null if not set).
     *
     * @return The due date of the task.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate The due date to set for the task.
     */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the task, including its status icon and title.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return getStatusIcon() + getTitle();
    }

    /**
     * Returns true if the task title contains the keyword provided by the user.
     * 
     * @param keyword The keyword the user wish to search for.
     * @return True if the task title contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        if (title.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }
}

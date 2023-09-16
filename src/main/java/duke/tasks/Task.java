package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * A task.
 */
public class Task {

    // Template Strings
    private static final String TASK_DISPLAY_TEMPLATE = "%s %s";
    private static final String TASK_EXPORT_TEMPLATE = "%s || %s";
    private static final String TASK_CHECKBOX_STYLE = "[%s]";
    private static final String TASK_CHECKBOX_UNCHECKED = " ";
    private static final String TASK_CHECKBOX_CHECKED = "X";

    // The description of the task.
    protected String description;

    // The status of the task. True if done, false if not done.
    protected boolean isDone = false;

    // The LocalDateTime formatter to parse the date/time of the task.
    protected final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // The LocalDateTime formatter to print the date/time of the task.
    protected final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH:mm");

    /**
     * Constructor for a Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the status of the checkbox for the task.
     *
     * @return String [ ] if not done, [X] if done.
     */
    private String getStatusCheckbox() {
        return isDone
                ? String.format(TASK_CHECKBOX_STYLE, TASK_CHECKBOX_CHECKED)
                : String.format(TASK_CHECKBOX_STYLE, TASK_CHECKBOX_UNCHECKED);
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format(TASK_DISPLAY_TEMPLATE, getStatusCheckbox(), description);
    }

    /**
     * Checks if the task description contains a keyword.
     *
     * @param keyword The keyword to be checked.
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean hasKeywordInDescription(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Exports the task to a String to be saved.
     *
     * @return String representation of the task to be saved.
     */
    public String export() {
        return String.format(TASK_EXPORT_TEMPLATE,
                isDone ? TASK_CHECKBOX_CHECKED : TASK_CHECKBOX_UNCHECKED,
                description);
    }

    /**
     * Updates the task with the new description.
     *
     * @param newDescription The new description of the task.
     */
    public void updateDescription(String newDescription) {
        this.description = newDescription;
    }
}

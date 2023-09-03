/**
 * Package name does not follow style guidelines due to Gradle issue.
 */
package Remy.Task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object with a description.
     * @param description Name of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Displays status icon [ ] or [X] depending on Task isDone value.
     * @return String [ ] or [X]
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

      /**
     * Marks Task as Done. Status icon: [X].
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as Undone. Status icon: [ ].
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * String representation of Task, including Status icon and Task name.
     * @return String representation of Task, including Status icon and Task name.
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}

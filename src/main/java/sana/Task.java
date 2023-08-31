package sana;
import java.time.LocalDate;

/**
 * Represents the task inputted by the user.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates an instance of task.
     *
     * @param description of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns status icon for task.
     *
     * @return X if task is done and whitespace if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task description.
     *
     * @return Task description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the formatted task string to be saved in the hard disk.
     *
     * @return formatted task string to be saved in the hard disk.
     */
    public String formatTask() {
        return " | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns the Task equivalent of a given formatted string task loaded from file.
     *
     * @param formattedTask task string that has been formatted in a specific manner from the file.
     * @return task equivalence of the formatted task string.
     */
    public static Task getTask(String formattedTask) {
        char type = formattedTask.charAt(0);
        boolean isDone = (formattedTask.charAt(4) == '1' ? true : false);
        String description = formattedTask.substring(8);

        switch (type) {
        case 'T':
            return new Todo(description, isDone);
        case 'D':
            int lastDescId = description.indexOf("|");
            LocalDate by = LocalDate.parse(description.substring(lastDescId + 2));
            description = description.substring(0, lastDescId - 1);
            return new Deadline(description, by, isDone);
        case 'E':
            String temp = description;
            lastDescId = description.indexOf('|');
            description = description.substring(0, lastDescId - 1);
            int lastFromId = temp.indexOf('|', lastDescId + 1);

            LocalDate from = LocalDate.parse(temp.substring(lastDescId + 2, lastFromId - 1));

            LocalDate to = LocalDate.parse(temp.substring(lastFromId + 2));
            return new Event(description, from, to, isDone);
        default:
            return null;
        }
    }

    /**
     * Indicates whether some other object is "equal to" this task.
     *
     * This method compares the string representations of two Task objects to determine
     * if they are equal. The comparison is based on the equality of the string
     * representations of the tasks.
     *
     * @param obj the reference object with which to compare.
     * @return true if this task is equal to the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;

            if (this == null || task == null) {
                return false;
            }

            return this.toString().equals(task.toString());
        }
        return false;
    }



}

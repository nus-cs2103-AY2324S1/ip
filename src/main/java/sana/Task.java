package sana;
import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

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
     * @return formatted task string to be saved in the hard disk.
     */
    public String formatTask() {
        return " | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Given a formatted task string, it returns the Task equivalent of that string.
     * @param formattedTask task string that has been formatted in a specific manner from the harddisk.
     * @return task equivalence of the formatted task string.
     */
    public static Task getTask(String formattedTask) {
        char type = formattedTask.charAt(0);
        boolean isDone = (formattedTask.charAt(4) == '1'? true : false);
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

package duke.task;

import duke.processors.FileHandler;

/**
 * Represents a task given from the user command.
 */
public class Task {
    protected String Description;
    protected boolean isDone;

    /**
     * A constructor for Task class.
     *
     * @param Description The description of the given task.
     */
    public Task(String Description) {
        this.Description = Description;
        this.isDone = false;
    }

    /**
     * Check if the task is done. If it is return [X] else [ ].
     *
     * @return the status of the task.
     */
    private String GetStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    /**
     * Change the status of the task to Done
     * and output an acknowledged string.
     *
     * @param fileHandler To update the status of the task in txt file.
     */
    public String MarkAsDone(FileHandler fileHandler) {
        String oldLine = this.toString();
        this.isDone = true;
        String newLine = this.toString();
        fileHandler.updateFile(oldLine, newLine);
        return "Nice! I've marked this task as done: \n"
                + "    " + this;
    }

    /**
     * change the status of the task to UnDone
     * and output an acknowledged string.
     *
     * @param fileHandler To update the status of the task in txt file.
     */
    public String MarkAsUnDone(FileHandler fileHandler) {
        String oldLine = this.toString();
        this.isDone = false;
        String newLine = this.toString();
        fileHandler.updateFile(oldLine, newLine);
        return "OK, I've marked this task as not done yet: \n"
                + "    " + this;
    }

    /**
     * Return task's description.
     *
     * @return String.
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * Return a string containing the status and description.
     *
     * @return return the icon and the description of the task.
     */
    public String toString() {
        return GetStatusIcon() + " " + Description;
    }
}

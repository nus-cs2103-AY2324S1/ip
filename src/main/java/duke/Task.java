package duke;

/**
 * This abstract class represents a task in the Duke program.
 */
public abstract class Task {

    /** Task description. */
    protected String description;
    /** Task is done. */
    protected boolean isDone = false;

    /**
     * Creates as task based on the Commands enum value and a string representing the description of the task.
     * If a task cannot be created from the given information, it throws a DukeException.
     *
     * @param command The Commands enum value representing the type of task to be created.
     * @param rawLine The description of the task to be created.
     * @return A Task object representing the created task.
     * @throws DukeException If a task cannot be created from the given information.
     */
    public static Task create(Commands command, String rawLine) throws DukeException {
        switch (command) {
        case todo:
            return ToDo.create(rawLine);
        case deadline:
            return Deadline.create(rawLine);
        case event:
            return Event.create(rawLine);
        default:
            throw new RuntimeException("Command not recognized"); //literally should not run
        }
    }
    /**
     * Creates the task by takes a string representing the description of the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * This method returns a string representing the done status of the task.
     *
     * @return A string representing the done status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gives a string representation of the task in file format.
     *
     * @return A string representation of the task in file format.
     */
    public abstract String fileString();

    /**
     * Checks if the description contains the substring
     *
     * @param s
     * @return true if found, false otherwise.
     */
    public boolean contains(String s) {
        return this.description.contains(s);
    }

    /**
     * Gives a string representation of the task in display format.
     *
     * @return A string representation of the task in display format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

package leon;

/**
 * The {@code Task} class. Contains all methods that are common to any generic task.
 */
public class Task {

    protected String details;
    protected boolean isCompleted;

    /**
     * Constructs a new {@code Task} object, with {@code isCompleted} set to false.
     *
     * @param details Details of the {@code Task}.
     */
    public Task(String details) {
        this.details = details;
        this.isCompleted = false;
    }

    /**
     * Loads a {@code Task} object that was previously stored in the hard disk.
     * The {@code isCompleted} parameter corresponds to the completion status in the last
     * instance of {@code Leon}.
     *
     * @param details     Details of the {@code Task}.
     * @param isCompleted Completion status of the {@code Task}.
     */
    public Task(String details, boolean isCompleted) {
        this.details = details;
        this.isCompleted = isCompleted;
    }


    /**
     * Sets the {@code Task} as completed.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Sets the {@code Task} as incomplete.
     */
    public void setIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Gets the details of the {@code Task}.
     *
     * @return Details of the {@code Task}.
     */
    protected String getDetails() {
        return this.details;
    }

    protected boolean getCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the string representation of the {@code Task}, to be
     * saved in the text file.
     *
     * @return Compressed string representation of the {@code Task}.
     */
    public String toFileSaveFormat() {
        return String.format("Task/%s/%c",
            this.getDetails(),
            this.isCompleted ? 'Y' : 'N');
    }

    /**
     * Returns the string representation of the {@code Task}, to be
     * printed in the {@code list} method.
     *
     * @return String representation of the {@code Task}.
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.details;
        } else {
            return "[ ] " + this.details;
        }
    }
}

package duke;

/**
 * A command class that represents input errors.
 */
public class TaskError extends Command{
    //The error type.
    private String error;

    /**
     * Instantiates a new TaskError Command
     * @param error
     */
    public TaskError(String error) {
        this.error = error;
    }

    /**
     * Returns the input error message
     * @return The input error message.
     */
    @Override
    public String execute() {
        return Ui.inputErrorMessage(this.error);
    }
}

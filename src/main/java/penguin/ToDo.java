package penguin;

/**
 * ToDo is a type of Task with no associated date.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    /**
     * Returns information about the task for output to the user.
     *
     * @return Information about the task in user-output format.
     */
    public String getDisplay() {
        return "[T]" + super.getDisplay();
    }
    /**
     * Returns information about the task for internal storage purposes.
     *
     * @return Information about the task in internal storage format.
     */
    public String getSaveDisplay() {
        return "T | " + super.getSaveDisplay();
    }
}

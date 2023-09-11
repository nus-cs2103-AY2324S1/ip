package duke;

/**
 * Task class that contains field such as name, completed and done & undone checkboxes
 */
public class Task {
    private static final String DONECHECKBOX = "[X] ";
    private static final String UNDONECHECKBOX = "[ ] ";
    private static final String completedNumber = "1";
    private static final String unCompletedNumber = "0";
    private String name;
    private boolean isCompleted;
    public Task(String name) {
        this.name = name;
    }

    //Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setUncompleted() {
        this.isCompleted = false;
    }

    public void update(String details) throws InvalidInputException {
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the correct checkbox according to the task completion
     * @return String value of checkbox
     */
    public String getCheckbox() {
        if (this.isCompleted) {
            return Task.DONECHECKBOX;
        }
        return Task.UNDONECHECKBOX;
    }
    public String newFormat() {
        return "";
    }
    /**
     * Returns string value of int based on completion
     * @return String value 1 if completed and 0 otherwise
     */
    public String getInt() {
        if (this.isCompleted) {
            return Task.completedNumber;
        } else {
            return Task.unCompletedNumber;
        }
    }

    /**
     * Returns confirmation message when a task is successfully added.
     * @param size the length of the TaskList
     * @return String of confirmation message
     */
    public String confirmation(int size) {
        String s1 = "Got it. I've added this task:\n";
        String s2 = "Now you have " + size + " tasks in the list.";
        return s1 + this.toString() + "\n" + s2;
    }

    /**
     * Returns confirmation message when a task is succesfully deleted.
     * @return String of deletion message
     */
    public String removed() {
        String s1 = "Noted. I've removed this task:";
        return (s1 + "\n" + this.toString());
    }
    /**
     * Executes the current task
     * @param lst the TaskList object we are adding this task to
     * @param ui Ui object
     * @param storage Storage object
     * @return empty String
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        return "";
    }
}

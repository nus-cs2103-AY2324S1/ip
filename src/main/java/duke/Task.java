package duke;

/**
 * Task class that contains field such as name, completed and done & undone checkboxes
 */
abstract class Task {
    private String name;
    private boolean completed;
    public static String doneCheckbox = "[X] ";
    public static String undoneCheckbox = "[ ] ";

    public Task(String name) {
        this.name = name;
    }

    //getters & setters
    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setUncompleted() {
        this.completed = false;
    }

    /**
     * To return the correct checkbox according to the task completion
     * @return String value of checkbox
     */
    public String getCheckbox() {
        if (this.completed) {
            return Task.doneCheckbox;
        } else {
            return Task.undoneCheckbox;
        }
    }
    abstract String newFormat();

    /**
     * To return string value of int based on completion
     * @return String value 1 if completed and 0 otherwise
     */
    public String getInt() {
        if (this.completed) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * To return confirmation message when a task is successfully added.
     * @param size the length of the TaskList
     * @return String of confirmation message
     */
    public String confirmation(int size) {
        String s1 = "Got it. I've added this task:\n";
        String s2 = "Now you have " + size + " tasks in the list.";
        return s1 + this.toString() + "\n" + s2;
    }

    /**
     * To return confirmation message when a task is succesfully deleted.
     * @return String of deletion message
     */
    public String removed() {
        String s1 = "Noted. I've removed this task:";
        return(s1 + "\n" + this.toString());
    }

    public void execute(TaskList lst, Ui ui, Storage storage) {};
}

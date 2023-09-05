package bareum;

/**
 * This class implements a todo with a description.
 */
public class TodoTask extends Task {
    /**
     * Creates a new todo using the input completion status and description.
     * @param isDone Completion status of todo.
     * @param description Description of todo.
     */
    private TodoTask(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Creates a new todo using the input from the user.
     * @param description Description of the todo
     * @return New uncompleted todo with the corresponding description.
     * @throws BareumException If description is empty.
     */
    static public TodoTask makeTodo(String description) throws BareumException {
        if (description.length() == 0) {
            throw new BareumException("Todo description cannot be empty.");
        }
        return new TodoTask(false, description);
    }

    /**
     * Creates a new todo using the input from a saved todo.
     * @param taskInputs Type, completion status, description and due date of the saved task.
     * @return New todo with the corresponding description.
     */
    static public TodoTask makeTodo(String[] taskInputs) {
        boolean isDone = taskInputs[0].equals("1");
        return new TodoTask(isDone, taskInputs[2]);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Create a string representation of the details of the todo for saving into the hard disk.
     * @return String representation of the details of the todo.
     */
    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "T|" + done + "|" + this.description + "\n";
    }
}
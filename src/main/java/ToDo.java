public class ToDo extends Task{
    /**
     * Construct a task with a given description. Completion of the task
     * is false by default
     *
     * @param description The description of the task.
     */
    private ToDo(String description) {
        super(description);
    }

    public static Task of(String description) throws NoTaskException {
        if(!description.isEmpty()) {
            return new ToDo(description);
        } else {
            throw new NoTaskException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns a formatted string of the status of the task
     * @return String containing completion status, type and description of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

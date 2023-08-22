public class ToDo extends Task{
    /**
     * Construct a task with a given description. Completion of the task
     * is false by default
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
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

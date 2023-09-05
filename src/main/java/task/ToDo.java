package task;

/**
 * This class represents the task type ToDo.
 */
class ToDo extends Task {

    /**
     * Creates a ToDo object.
     *
     * @param taskDescription The name of the task.
     * @return Returns a Task object.
     */
    ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String write() {
        return "todo " + super.getTaskDescription() + "\n";
    }
}
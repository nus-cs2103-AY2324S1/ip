package duke.tasks;

class Todo extends Task {
    private final char taskType = 'T';

    public Todo(String name) {
        super(name);
    }

    /**
     * Return string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        String checkBox;
        String taskType = String.format("[%c]", this.taskType);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}
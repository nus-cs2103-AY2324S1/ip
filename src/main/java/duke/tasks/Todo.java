package duke.tasks;

class Todo extends Task {

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
        char taskType1 = 'T';
        String taskType = String.format("[%c]", taskType1);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}
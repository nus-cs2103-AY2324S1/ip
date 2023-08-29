package duke.task;

import duke.enums.TaskType;

public class ToDoTask extends Task {

    /**
     * Constructor for ToDoTask
     *
     * @param description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }


    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor() + String.format("| %s ", TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

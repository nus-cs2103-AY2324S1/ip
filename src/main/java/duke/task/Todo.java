package duke.task;

import duke.task.Task;

public class Todo extends Task {

    /**
     * Constructor of the  task
     * @param description Description of the  task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

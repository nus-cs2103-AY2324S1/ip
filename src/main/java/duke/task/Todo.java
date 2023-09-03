package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        int completed = this.isDone ? 1 : 0;
        return "T " + "| " + completed + " | " + this.description + "\r\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


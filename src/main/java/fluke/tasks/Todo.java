package fluke.tasks;

import fluke.exceptions.FlukeException;

public class Todo extends Task {
    public Todo(String description) throws FlukeException {
        super(description);
    }

    public Todo(String description, boolean isDone) throws FlukeException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

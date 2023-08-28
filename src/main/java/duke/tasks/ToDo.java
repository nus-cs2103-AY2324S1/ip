package duke.tasks;

import duke.Exceptions.IncompleteDescriptionException;

public class ToDo extends Task {

    public ToDo(String taskName) throws IncompleteDescriptionException {
        super(taskName);
    }

    public static ToDo create(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) throw new IncompleteDescriptionException();
        return new ToDo(taskName);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "T" + " | " + isDoneChar + " | " + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

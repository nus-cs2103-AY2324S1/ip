package duke.task;

import duke.exception.EmptyTaskDescException;

/**
 * Represents a to-do task that has a name.
 * Extends the {@link Task} class.
 */
public class Todo extends Task {
    public Todo(String task) throws EmptyTaskDescException {
        super(task);
    }
    public static Todo unpackSaveFormat(String savedTask) throws EmptyTaskDescException {
        String[] components = savedTask.split("\\|", 2);
        Todo task = new Todo(components[0]);
        if (components[1].equals("true")) {
            task.setCompleted();
        } else {
            task.setUncompleted();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String toSaveFormat() {
        return "todo:" + super.toSaveFormat();
    }
}

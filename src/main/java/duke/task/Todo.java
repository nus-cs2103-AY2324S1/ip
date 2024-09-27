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
    /**
     * Returns a To-do object parsed from the save format.
     * @param savedTask The string representing the task in the save format.
     * @return A new To-do object.
     */
    public static Todo parseSaveFormat(String savedTask) throws EmptyTaskDescException {
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

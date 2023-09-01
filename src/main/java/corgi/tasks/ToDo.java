package corgi.tasks;

import corgi.parsers.TaskParser;
/**
 * Todo task, a type of task without any date/time attached to it.
 */
public class ToDo extends Task {
    
    /**
     * Initializes a new todo task with the given description. The task's initial status is set to not done.
     *
     * @param desc The description of the todo task
     */
    public ToDo(String desc) {
        super(false, desc);
    }

    /**
     * Initializes a new todo task with the given status and description.
     *
     * @param status The status of the task.
     * @param desc The description of the task.
     */
    public ToDo(boolean status, String desc) {
        super(status, desc);
    }

    /**
     * Converts the todo task to a storable string representation.
     *
     * @return A string representing the to-do task in a storable format.
     */
    @Override
    public String toStorableString() {
        String[] infos = {"T", this.status ? "1" : "0", this.desc};

        return String.join(TaskParser.SEPARATOR, infos);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return Task type, status icon and description of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

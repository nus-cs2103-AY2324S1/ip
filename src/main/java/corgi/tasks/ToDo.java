package corgi.tasks;

import corgi.parsers.TaskParser;
/**
 * Todo task, a type of task without any date/time attached to it.
 */
public final class ToDo extends Task {
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
     * @param isDone The status of the task.
     * @param desc The description of the task.
     */
    public ToDo(boolean isDone, String desc) {
        super(isDone, desc);
    }

    @Override
    public ToDo markAsDone() throws TaskStatusException {
        if (this.isDone) {
            throw new TaskStatusException("The task is already marked as done.");
        }
        return new ToDo(true, desc);
    }

    @Override
    public ToDo markAsNotDone() throws TaskStatusException {
        if (!this.isDone) {
            throw new TaskStatusException("The task is already marked as not done.");
        }
        return new ToDo(false, desc);
    }

    /**
     * Converts the todo task to a storable string representation.
     *
     * @return A string representing the to-do task in a storable format.
     */
    @Override
    public String toStorableString() {
        String statusStr = this.isDone ? "1" : "0";

        String[] infos = {"T", statusStr, this.desc};
        String combinedInfos = String.join(TaskParser.SEPARATOR, infos);

        return combinedInfos;
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

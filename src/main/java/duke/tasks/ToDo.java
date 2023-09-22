package duke.tasks;

import duke.exceptions.DukeException;
import duke.ronaldo.TaskType;

/**
 * Class to handle todo tasks
 */
public class ToDo extends Task {
    public ToDo(String list, TaskType type) {
        super(list, type);
    }

    /**
     * Marks the task as completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    @Override
    public String setMarked() throws DukeException {
        super.setMarked();
        return "SUI, Nice! I've marked this task as done:\n" + this;
    }

    /**
     * Marks the task as not completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    @Override
    public String setUnmarked() throws DukeException {
        super.setUnmarked();
        return "SUI, OK, I've marked this task as not done yet:\n" + this;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

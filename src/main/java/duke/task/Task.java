package duke.task;

import java.util.ArrayList;
import duke.DukeException;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** Whether the task is marked as done. */
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Reads task information from a file and adds it to the task list.
     *
     * @param arr The array of task information from the file.
     * @param list The ArrayList to which the tasks will be added.
     * @throws DukeException If there's a problem with the file or task data.
     */
    public static void readListFromFile(String[] arr, ArrayList<Task> list) throws DukeException {
        if (arr.length != 3) {
            throw new DukeException("Uh Oh! There seems to be a problem with the file!\n" +
                    "Some of the tasks may be gone! Sorry!!\n");
        }

        String type = arr[0].strip();
        String description = arr[2].strip();
        String isMarked = arr[1].strip();
        if (type.equals("T")) {
            ToDo.addToDoFromFile(description, list, isMarked);
        } else if (type.equals("D")) {
            Deadline.addDeadlineFromFile(description, list, isMarked);
        } else if (type.equals("E")) {
            Event.addEventFromFile(description, list, isMarked);
        }
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return "Task";
    }

    /**
     * Marks the task based on the value read from a file.
     *
     * @param isMarked The value indicating whether the task is marked.
     */
    public void markFromRead(String isMarked) {
        if (isMarked.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the status of the task from the file.
     *
     * @return The status of the task (1 for done, 0 for not done).
     */
    public int getStatusFromFile() {
        return (isDone ? 1 : 0);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the task for writing to a file.
     *
     * @return A string representation of the task for file output.
     */
    public String toStringFile() {
        return getStatusFromFile() + " | " + description;
    }
}

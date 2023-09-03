package duchess;

/**
 * An abstract class representing a Task.
 */
public abstract class Task {
    public static final String SAVE_STRING_DELIMITER = "\\|";

    private String name;
    private TaskStatus status;

    /**
     * Returns a new Task object with the given parameters.
     *
     * @param name - the name of the Task.
     * @param TaskStatus - the status of the Task.
     */
    Task(String name, TaskStatus status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Returns a new Task object with the given parameters and a default TaskStatus of unmarked.
     *
     * @param name - the name of the Task.
     */
    Task(String name) {
        this.name = name;
        this.status = TaskStatus.UNMARKED;
    }

    /**
     * Factory method of Task, creating a Task by parsing a String that represents a Task.
     *
     * @param s - the String that represents the Task.
     * @return    either the Task that was parsed successfully using the String, or null if the string is
     *            an invalid Task.
     */
    public static Task parseSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);

        // Check for the types
        if (splitString[0].equals("T")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return ToDo.fromSaveString(s);
        } else if (splitString[0].equals("D")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return Deadline.fromSaveString(s);
        } else if (splitString[0].equals("E")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return Event.fromSaveString(s);
        }

        return null;
    }

    /**
     * Changes this Task's status to the provided TaskStatus.
     *
     * @param newStatus - the new TaskStatus for this task's status to be changed to.
     */
    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns the name of this Task.
     *
     * @return the name of this Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a String representation of this task.
     *
     * @return the String representation of this task.
     */
    public String toString() {
        if (this.status == TaskStatus.UNMARKED) {
            return String.format("[ ] %s", this.name);
        }
        if (this.status == TaskStatus.MARKED) {
            return String.format("[X] %s", this.name);
        }
        return "";
    }

    /**
     * Returns a String representation of this task, for the purposes of data saving.
     */
    public String toSaveString() {
        if (this.status == TaskStatus.UNMARKED) {
            return String.format("0|%s", this.name);
        }
        if (this.status == TaskStatus.MARKED) {
            return String.format("1|%s", this.name);
        }
        return "";
    }
}

package shiba.tasks;

import java.util.List;

import shiba.exceptions.InvalidCommandException;

/**
 * Represents a task that can be added to the task list.
 */
public abstract class ShibaTask {
    protected enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected final String name;
    protected boolean isDone;
    private final TaskType type;

    protected ShibaTask(String name, TaskType type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as done, if not already done.
     *
     * @return True if the task was not already done, else false.
     */
    public boolean markDone() {
        if (isDone) {
            return false;
        }
        isDone = true;
        return true;
    }

    /**
     * Marks the task as not done, if already done.
     *
     * @return True if the task was already done, else false.
     */
    public boolean markNotDone() {
        if (!isDone) {
            return false;
        }
        isDone = false;
        return true;
    }

    /**
     * Gets the label in the form of [X] for the task type.
     *
     * @return The label for the task type.
     */
    private String getTaskLabel() {
        switch (type) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            break;
        }

        return "[?]";
    }

    /**
     * Converts the task to a string to be saved to disk.
     *
     * @return A string representation of the task.
     */
    public abstract String toSaveString();

    /**
     * Parses a task from the provided string parameters.
     *
     * @param params List of string parameters to parse tasks from.
     * @return The parsed task, or null if the task cannot be parsed from the provided parameters
     *     due to insufficient or incorrect parameters.
     */
    public static ShibaTask fromSaveParams(List<String> params) {
        if (params.size() < 3) {
            return null;
        }
        ShibaTask parsedTask;
        boolean isDone = params.get(1).equals("1");
        String name = params.get(2);
        try {
            switch (params.get(0)) {
            case "T":
                parsedTask = new TodoTask(name);
                break;
            case "D":
                if (params.size() < 4) {
                    return null;
                }
                parsedTask = new DeadlineTask(name, params.get(3));
                break;
            case "E":
                if (params.size() < 5) {
                    return null;
                }
                parsedTask = new EventTask(name, params.get(3), params.get(4));
                break;
            default:
                return null;
            }
        } catch (InvalidCommandException e) {
            return null;
        }

        if (isDone) {
            parsedTask.markDone();
        }

        return parsedTask;
    }

    /**
     * Checks if the task name contains the specified keyword.
     *
     * @param keyword The keyword to check for.
     * @return True if the task name contains the keyword, else false.
     */
    public boolean containsKeyword(String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getTaskLabel() + "[" + (isDone ? "X" : " ") + "] " + name;
    }
}

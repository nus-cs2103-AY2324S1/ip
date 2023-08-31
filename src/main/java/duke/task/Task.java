package duke.task;

import java.util.ArrayList;


/**
 * Represents a general task with a name and completion status.
 * This class serves as an abstract base for more specific types of tasks.
 */
public abstract class Task {

    /**
     * Completion status of the task.
     */
    protected boolean done;

    /**
     * Name or description of the task.
     */
    protected String name;

    protected ArrayList<ArrayList<Integer>> tracker = new ArrayList<ArrayList<Integer>>(128);

    /**
     * Constructs a new Task object with a specified name.
     * The task is initially set as not completed.
     *
     * @param name Name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        for (int i = 0; i < 128; i++) {
            tracker.add(new ArrayList<>());
        }
        for (int i = 0; i < name.length(); ++i) {
            tracker.get((int) name.charAt(i)).add(i);
        }
        this.done = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        done = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        done = false;
    }

    /**
     * Converts the task to a formatted string suitable for saving or other specific representations.
     * This method should be overridden by subclasses to provide specific formatting.
     *
     * @return Formatted string representing the task.
     */
    public abstract String stringifyTask();

    public boolean find(String arg) {
        int k = arg.charAt(0);
        for (int i : tracker.get(k)) {
            boolean cannot = false;
            for (int j = 0; j < arg.length(); j++) {
                if (i + j >= name.length()) {
                    cannot = true;
                    break;
                }
                if (name.charAt(i + j) != arg.charAt(j)) {
                    cannot = true;
                    break;
                }
            }
            if (!cannot) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the task with its completion status and name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

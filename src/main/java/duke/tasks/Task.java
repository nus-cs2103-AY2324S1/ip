package duke.tasks;

import java.time.LocalDate;

/**
 * The Task that encapsulates its description and its completion state.
 */
public abstract class Task {
    private boolean done;
    private final String name;

    private Task() {
        this.name = "";
        done = false;
    }

    /**
     * Instantiates a new Task.
     *
     * @param name the description of the Task
     */
    public Task(String name) {
        this.name = name;
        done = false;
    }

    /**
     * Sets the Task as completed.
     */
    public void setdone() {
        done = true;
    }

    /**
     * Returns the completions status of the Task.
     *
     * @return a boolean for completions status
     */
    public boolean isdone() {
        return this.done;
    }

    /**
     * Return the description of the task.
     *
     * @return the Task description
     */
    public String getname() {
        return this.name;
    }

    /**
     * Checks if a given String key is contained in the description of task
     *
     * @param str the str
     * @return the boolean
     */
    public boolean containsStr(String str) {
        return name.toLowerCase().contains(str.toLowerCase());
    }

    /**
     * Returns the String description of a task suitable for file storage.
     *
     * @return the task String
     */
    public abstract String dataString();

    /**
     * Checks if the task occurs on the day given.
     *
     * @param date the date
     * @return the boolean for if the date matches
     */
    public abstract boolean checkDate(LocalDate date);

    @Override
    public String toString() {
        String box;

        if (done) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }

        return box + name;
    }
}

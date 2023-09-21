package noac.task;

import java.util.ArrayList;

/**
 * Base Task class with description on what to do and
 * boolean for whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructor to initialise the task.
     *
     * @param description Description on what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Returns 'X' for done task and space for undone task for printing to user.
     *
     * @return The string as described.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets isDone to true to mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets isDone to false to mark the task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Tags the task with the given tag.
     *
     * @param tag The tag to be added to the task.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     *  Checks whether the task contains the tag.
     *
     * @param tag The tag to be checked.
     * @return Whether the task contains the tag or not.
     */
    public boolean containsTag(String tag) {
        for(int i = 0; i < this.tags.size(); i++) {
            if (tag.equals(this.tags.get(i))) {
                return true;
            }
        }
        return false;
    }

    public String tagsToString() {
        if(tags.isEmpty()) {
            return " ";
        }
        return String.join(" ", tags);
    }

    /**
     * Converts the task to string.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + this.description;
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    public String printToFile() {
        int i = isDone ? 1 : 0;
        return i + "|" + description;
    }
}
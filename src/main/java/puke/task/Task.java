package puke.task;

import puke.PukeException;

/**
 * A class that represents an action that has to be tracked and carried out at a specified or unspecified time.
 */
public class Task {
    protected String tag;
    protected boolean done;
    protected String description;
    protected Task(String tag, String description) throws PukeException {
        this.tag = tag;
        this.description = description;
        this.done = false;
        if (tag.isEmpty() || description.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks a task as undone
     */
    public void unmark() {
        this.done = false;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Returns a String representation of the task that can be stored and
     * read later when the program is initiated again.
     *
     * @return a String representation.
     */
    public String write() {
        int checked = 0;
        if (this.done) {
            checked = 1;
        }
        return String.format("%s/%d/%s", this.tag, checked , this.description);
    }

    /**
     * Returns a String representation to be displayed in the to do list.
     *
     * @return a String representation.
     */
    @Override
    public String toString() {
        String status = "[ ]";
        if (done) {
            status = "[X]";
        }
        return String.format("%s%s %s", this.tag, status, this.description);
    }
}

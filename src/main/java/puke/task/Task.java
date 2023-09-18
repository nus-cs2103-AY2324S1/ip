package puke.task;

import java.util.ArrayList;
import java.util.List;

import puke.managers.PukeException;

/**
 * A class that represents an action that has to be tracked and carried out at a specified or unspecified time.
 */
public class Task {
    protected String label;
    protected boolean isDone;
    protected String description;
    protected ArrayList<String> tags;
    protected Task(String label, String description) throws PukeException {
        this.label = label;
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<String>();
        if (label.isEmpty() || description.isEmpty()) {
            throw new PukeException();
        }
    }

    protected Task(String label, String description, String[] tags) throws PukeException {
        this.label = label;
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<String>(List.of(tags));
        if (label.isEmpty() || description.isEmpty()) {
            throw new PukeException();
        }
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        assert (!isDone);
        isDone = true;
    }

    /**
     * Marks a task as undone
     */
    public void unmark() {
        assert (isDone);
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Adds a tag to this task
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns a sequence of all tags in order as a string
     * @return the string representation
     */
    public String printTags() {
        StringBuilder output = new StringBuilder();
        for (String a: tags) {
            output.append(String.format("#%s ", a));
        }
        return output.toString();
    }
    /**
     * Returns a String representation of the task that can be stored and
     * read later when the program is initiated again.
     *
     * @return a String representation.
     */
    public String write() {
        int checked = 0;
        if (isDone) {
            checked = 1;
        }
        return String.format("%s/%d/%s", label, checked , description);
    }

    /**
     * Returns a String representation of the tags of the task to be stored.
     * @return a String representation of the tags
     */
    public String writeTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag: tags) {
            sb.append("/").append(tag);
        }
        return sb.toString();
    }

    /**
     * Returns a String representation to be displayed in the to do list.
     *
     * @return a String representation.
     */
    @Override
    public String toString() {
        String status = "[ ]";
        if (isDone) {
            status = "[X]";
        }
        return String.format("%s%s %s", label, status, this.description);
    }
}

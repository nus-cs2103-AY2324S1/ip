package duke.tasks;

import java.util.ArrayList;

/**
 * Parent class of all the Tasks type.
 */
public class Task {
    /**
     * description contains the name of the task
     *
     * isDone contains whether the task is done
     *
     * tags contain a list of tags for the task
     */
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructor for the Task class.
     *
     * @param description The name of the Task.
     * @param isDone If task is completed.
     * @param tags List of tags to be added.
     */
    public Task(String description, boolean isDone, ArrayList<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Returns the icon that represents the isDone state.
     *
     * @return "X" for done or " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getTags() {
        String tagList = "";
        if (tags.size() == 0) {
            return tagList;
        }
        for (int i = 0; i < tags.size(); i++) {
            tagList += " #" + tags.get(i);
        }
        return tagList;
    }
    /**
     * Returns the String representation of the Task, along with the
     * indication of whether it isDone.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description + getTags();
    }

    /**
     * Returns the String to be written into the .txt file for saving
     * of the file.
     *
     * @return String to be written into the save file.
     */
    public String write() {
        String complete = isDone ? "1" : "0";
        if (getTags().equals("")) {
            return complete + " | " + this.description;
        }
        return complete + " | " + this.description + " | " + getTags();
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setTags(String[] tags) {
        for (String tag : tags) {
            boolean isContains = this.tags.contains(tag);
            if (isContains) {
                continue;
            }
            this.tags.add(tag);
        }
    }

    /**
     * Removes specific tags.
     *
     * @param tags List of tags to be removed from tags.
     */
    public void removeTags(String[] tags) {
        for (String tag : tags) {
            boolean isContains = this.tags.contains(tag);
            if (!isContains) {
                continue;
            }
            this.tags.remove(tag);
        }
    }
}

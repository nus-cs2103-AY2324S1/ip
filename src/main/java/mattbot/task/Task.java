package mattbot.task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Skeleton for Tasks. Deadlines, Events and Todos are examples of such tasks.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructor for a primitive Task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.tags = new ArrayList<String>();
    }

    /**
     * Alternative constructor for a task with tags.
     * @param name Name of Task
     * @param isDone Is the task done?
     * @param tags Any additional tags to populate it with.
     */
    Task(String name, boolean isDone, String... tags) {
        this.name = name;
        this.isDone = isDone;
        this.tags = (ArrayList<String>) Arrays.asList(tags);
    }

    /**
     * Add a tag as specified.
     * @param tag String containing tag text.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Removes tag as specified.
     * @param tag String with the exact tag.
     */
    public void removeTag(String tag) {
        if (!this.hasTag(tag)) {
            return;
        }
        for (int i = 0; i < this.tags.size(); i++) {
            if (this.tags.get(i).equals(tag)) {
                this.tags.remove(i);
                return;
            }
        }
        return;
    }

    /**
     * Finds if a tag is present.
     * @param findTag Tag to be found
     * @return Boolean value, if the tag is in the object.
     */
    public boolean hasTag(String findTag) {
        for (String tag : tags) {
            if (findTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Marks the current Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String of the current status of the Task.
     *
     * @return String "X" if the Task is done, " " otherwise.
     */
    public String showStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a String with the name of the object.
     *
     * @return String with name of the object.
     */
    public String showName() {
        return this.name;
    }
    /**
     * Identifies itself as an Event.
     *
     * @return Character to identify the type of Task.
     */
    public abstract String identifier();

    /**
     * Returns String form for storage.
     *
     * @return String for storage format.
     */
    public abstract String toFile();

    /**
     * Returns a String describing the doneness of a Task as a String.
     *
     * @return String "1" if task is complete, "0" otherwise.
     */
    public String showStatusAsFile() {
        return (isDone ? "1" : "0");
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.identifier(), this.showStatus(), this.showName());
    }
}

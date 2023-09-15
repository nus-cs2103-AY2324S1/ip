package duke;

import java.util.ArrayList;

/**
 * A class containing information about each task
 * They have a name, and a boolean to check
 * whether the task is done
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    /**
     * Create task based on description
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create task based on description and tags
     *
     * @param description The description of the task
     * @param tags The tags of the task
     */
    public Task(String description, String[] tags) {
        this.description = description;
        this.isDone = false;
        for (String tag : tags) {
            // Just a precaution to prevent adding empty strings as tags
            if (!tag.equals("")) {
                this.tags.add(tag);
            }
        }
    }

    /**
     * Returns a string depending on whether the task is done
     *
     * @return X if the task is done, a space if it isn't
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Mark the task as complete/incomplete respectively
     */
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Get the description of the task
     *
     * @return The description of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description + this.printTags();
    }

    /**
     * Print out the tags of the task
     *
     * @return The tags in order (with a hashtag in front, to differentiate from description)
     */
    private String printTags() {
        String out = " ";

        for (String tag : this.tags) {
            out += "#" + tag + " ";
        }

        return out;
    }

    /**
     * Add a tag to the taglist
     *
     * @param tag The tag to be added
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes the specified tag from the taglist
     *
     * @param tag The tag to be removed
     * @throws IllegalArgumentException if the tag cannot be found
     */
    public void removeTag(String tag) throws IllegalArgumentException {
        if (tags.contains(tag)) {
            tags.remove(tag);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Create a clone of the taglist
     *
     * @return A copy of the taglist
     */
    @SuppressWarnings("unchecked")
    public ArrayList<String> getTags() {
        return (ArrayList<String>) tags.clone();
    }
}

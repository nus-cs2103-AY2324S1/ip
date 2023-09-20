package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents a Task to be done by the user.
 */
public class Task {
    private String title;
    private boolean isDone;
    private Optional<ArrayList<Tag>> tagOptional;
    /**
     * Validates the title and creates a Task object.
     *
     * @param title of the task.
     * @throws DukeException if the title is blank.
     */

    public Task(String title, Optional<ArrayList<Tag>> tagOptional) throws DukeException {
        if (title.isBlank()) {
            throw new DukeException("    Title of a " + this + " cannot be blank...");
        }
        this.title = title;
        this.isDone = false;
        this.tagOptional = tagOptional;
    }

    public boolean hasTag(Tag t) {
        if (tagOptional.isPresent()) {
            ArrayList<Tag> tagList = tagOptional.get();
            return tagList.contains(t);
        }

        return false;
    }
    /**
     * Sets the done value of the Task, which indicates if the task is done.
     *
     * @param isDone indicates whether the task is done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Optional<ArrayList<Tag>> getTagOptional() {
        return this.tagOptional;
    }

    /**
     * Returns the String value of the status of the task, which includes the done state, the title, and the tags.
     *
     * @return status of the task.
     */
    public String getStatus() {
        String status =  "[" + (this.isDone ? "X" : " ") + "] " + this.title;
        if (!this.tagOptional.isPresent()) {
            return status;
        }

        String tagMessageFormat = "(tags: %1$s)";
        String tags = "";
        for (Tag tag: this.tagOptional.get()) {
            tags += " " + tag;
        }
        tags = tags.strip();
        String tagMessage = String.format(tagMessageFormat, tags);
        status += tagMessage;
        return status;
    }

    /**
     * Returns the title of the task in String.
     *
     * @return title of the task.
     */
    public String toString() {
        return title;
    }
}

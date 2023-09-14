package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Encapsulates different types of tasks that have a name and whether it is done.
 */
public abstract class Task {
    private String name;
    private boolean isDone;
    private ArrayList<String> tags = new ArrayList<>();

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns all tags as a string of tags
     * @return string of tags in form #tag1 #tag2
     */
    public String getTags() {
        StringBuilder tagsString = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            tagsString.append("#" + tags.get(i) + " ");
        }

        return tagsString.toString();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Marks task as done
     * @return string to inform user that task was marked as done
     */
    public String markAsDone() {
        this.isDone = true;
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(this.toString() + "\n");
        return output.toString();
    }

    /**
     * Marks task as undone
     * @return string to inform user that task was marked as undone
     */
    public String markAsUndone() {
        this.isDone = false;
        StringBuilder output = new StringBuilder("OK, I've marked this task as not done yet:\n");
        output.append(this.toString() + "\n");
        return output.toString();
    }

    /**
     * {@inheritDoc}
     * Returns string representation of task, including whether task is done and its name
     * @return string representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    /**
     * Generates task list entry in specified format.
     * @return task list entry as a string
     */
    public abstract String toTaskListEntry();
}

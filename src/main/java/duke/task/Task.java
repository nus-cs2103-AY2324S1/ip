package duke.task;

import java.util.ArrayList;

/**
 * Encapsulates of data related to a single task of user
 *
 * @author Lian Zhi Xuan
 */
public class Task {

    private String name;
    private boolean done;

    private ArrayList<String> tags;

    public Task(String task) {
        name = task;
        done = false;
        tags = new ArrayList<>();
    }

    /**
     * Show the type of task
     *
     * @return a string indicating its type
     */
    public String getType() {
        return "duke/task";
    }

    /**
     * Returns what task is in this object
     *
     * @return task name
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Tags this task as done
     *
     */
    public void mark() {
        done = true;
    }

    /**
     * Tags this task as not done
     *
     */
    public void unmark() {
        done = false;
    }

    /**
     * Check if the task is done
     *
     * @return true if task is done
     */
    public boolean isDone() {
        return done;
    }


    /**
     * Return the string representation of isDone()
     *
     * @return a string marker indicating status of task
     */
    public String getStatus() {
        return done ? "[X]" : "[ ]";
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void assignTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public boolean hasTag(String tag) {
        for (String t : tags) {
            if (tag.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Task) {
            Task temp = (Task)o;
            return this.name.equals(temp.getTaskName()) && this.done == temp.isDone();
        }
        return false;
    }
}

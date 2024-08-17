package duke.task;

import java.util.ArrayList;

/**
 * Encapsulates of data related to a single task of user
 *
 * @author Lian Zhi Xuan
 */
public class Task {

    private String name;
    private boolean isDone  ;

    private ArrayList<String> tags;

    public Task(String task) {
        name = task;
        isDone = false;
        tags = new ArrayList<>();
    }

    /**
     * Shows the type of task
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
        isDone = true;
    }

    /**
     * Tags this task as not done
     *
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Checks if the task is done
     *
     * @return true if task is done
     */
    public boolean isDone() {
        return isDone;
    }


    /**
     * Returns the string representation of isDone()
     *
     * @return a string marker indicating status of task
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
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
            return this.name.equals(temp.getTaskName()) && this.isDone == temp.isDone() && this.isTagsEqual(temp);
        }
        return false;
    }


    public boolean isTagsEqual(Task t) {
        if (tags.size() != t.getTags().size()) {
            return false;
        }
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).equals(t.getTags().get(i))) {
                continue;
            }
            return false;
        }

        return true;
    }
}

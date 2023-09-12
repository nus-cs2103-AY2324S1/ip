package task;

import java.io.Serializable;
import java.util.List;

/**
 * Class encapsulating a single task to be done (by the user) in kniaz.Kniaz
 */
public abstract class Task implements Serializable {

    /**
     * Whether this task is done
     */
    private boolean isDone = false;


    /**
     * Name of this task
     */
    private String taskName = "";

    /**
     * Tag of this task
     */
    private List<String> tags;

    /**
     * The (protected) constructor for this task, mostly for inheritance by subclasses
     * @param taskName name of task
     * @param isDone whether it is done
     */
    protected Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }



    /**
     * Marks this task as done, does NOT check for if it was already done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done, does NOT check for if it was already undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * The user-facing string representation of this Task, containing information about
     * whether this task is done and its name
     * @return the user-facing string representation of this ToDo.
     */
    public String toPrintString() {
        String statusIcon = "";
        if (this.isDone) {
            statusIcon = "X";
        } else if (!this.isDone) {
            statusIcon = " ";
        }
        return String.format("[%s] %s", statusIcon, this.taskName);
        // return in format [statusIcon] taskname
    }

    /**
     * Gets task name of this task
     * @return the task name of this task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets tag(s) of this class
     * @return the tag(s) of this class
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Adds a tag to this task
     * @param tag the tag of this class
     * @return whether the adding succeeded
     */
    public boolean addTag(String tag) {
        return this.tags.add(tag);
    }
}

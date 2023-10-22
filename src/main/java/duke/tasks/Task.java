package duke.tasks;

/**
 * Task class, superclass to Todo, Deadline and Event subclasses
 */
public class Task {
    //total number of tasks
    protected static int size = 0;
    protected String description;
    protected boolean isDone;
    protected String tag = "no tag";

    /**
     * Creates new Task Object
     *
     * @param description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size = size + 1;
    }

    /**
     * Get description of task
     *
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get status of task
     *
     * @return String "X" if done, "" if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true or false depending on user input
     *
     * @param action string mark or unmark
     */
    public String setAction(String action) {
        String result = "";
        if (action.equals("mark")) {
            this.isDone = true;
            result = "Nice! I've marked this task as done:\n";
        } else if (action.equals("unmark")) {
            this.isDone = false;
            result = "OK, I've marked this task as not done yet:\n";
        }
        result += "  " + this;
        return result;
    }

    /**
     * Sets Tag for the task
     *
     * @param newTag tag description
     * @return response String
     */
    public String setTag(String newTag) {
        this.tag = newTag;
        return ("#" + newTag + " has been set!");
    }

    /**
     * Reduce size of tasks and return response with remaining number of tasks
     *
     * @return String response
     */
    public String delete() {
        size = size - 1;
        String ret = "";
        ret += "Noted. I've removed this task:\n";
        ret += "  " + this + "\n";
        ret += "Now you have " + size + " tasks in the list.\n";
        return ret;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.description;
    }
}
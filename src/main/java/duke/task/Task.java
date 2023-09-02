package duke.task;

/**
 * Encapsulates of data related to a single task of user
 *
 * @author Lian Zhi Xuan
 */
public class Task {

    private String name;

    private boolean done;

    public Task(String task) {
        name = task;
        done = false;
    }

    /**
     * Show the type of task
     *
     * @return a string indicating its type
     */
    public String type() {
        return "duke/task";
    }

    /**
     * Returns what task is in this object
     *
     * @return task name
     */
    public String taskName() {
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
    public String status() {
        return done ? "[X]" : "[ ]";
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Task) {
            Task temp = (Task)o;
            return this.name.equals(temp.taskName()) && this.done == temp.isDone();
        }
        return false;
    }
}

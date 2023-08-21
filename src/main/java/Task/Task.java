package Task;

/**
 * Class encapsulating a single task to be done (by the user) in Kniaz
 */
public class Task {

    /**
     * Whether this task is done
     */
    private boolean isDone = false;

    /**
     * Name of this task
     */
    private String taskName = "";

    /**
     * The private constructor for this task, initialisation will be through factory methods
     * @param taskName name of task
     * @param isDone whether it is done
     */
    private Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Factory method for Task, creates an undone new task with the supplied name
     * @param taskName the name of the task to be created
     * @return the task that was created by this method
     */
    public static Task newTask(String taskName) {
        return new Task(taskName, false);
    }


    /**
     * Marks this task as done, does NOT check for if it was already done
     */
    public void markAsDone() {
        this.isDone = true  ;
    }

    /**
     * Marks this task as not done, does NOT check for if it was already undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * Returns the string representation of Task
     * @return the string representation of task
     */
    @Override
    public String toString() {
        String statusIcon = "";
        if (this.isDone) {
            statusIcon = "X";
        } else if (!this.isDone) {
            statusIcon = " ";
        }
        return String.format("[%s] %s", statusIcon, this.taskName);
        // return in format [statusIcon] taskname
    }

}

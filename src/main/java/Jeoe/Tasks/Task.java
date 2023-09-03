package Jeoe.Tasks;

/**
 * This class encapsulates the Task class.
 * It represents a task that is added to the Jeoe program upon a command by the user that creates a task.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Task {

    /** Description of the task. */
    private String description; // the toString handles the space after the [ ] or [X]

    /** Status of whether the task is done. */
    private boolean isDone = false;

    /** Enum type TaskType which lists the different task types. */
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    /** TaskType of the task. */
    private TaskType taskType;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     * @param taskType The task type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    /**
     * Returns the done status of the task.
     *
     * @return Status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /** Un-marks the task to be not done. */
    public void unmark() {
        this.isDone = false;
    }

    /** Marks the task to be done. */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Returns the type of the task.
     *
     * @return Type of the task.
     */
    public TaskType taskType() {
        return this.taskType;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the start date & time of the task.
     *
     * @return String representation of the start date & time of the task.
     */
    public String getStartDateTimeString() {
        // can consider removing this method by typecasting
        return null;
    }

    /**
     * Returns the end date & time of the task.
     *
     * @return String representation of the end date & time of the task.
     */
    public String getEndDateTimeString() {
        // can consider removing this method by typecasting
        return null;
    }

    /**
     * Returns the String to be returned when task is added to the list of tasks.
     *
     * @param currNumOfTask Current number of tasks in the list.
     * @return String to be returned when task is added to the list of tasks.
     */
    public String replyString(int currNumOfTask) {
        return "Got it. I've added this task:\n  "
                + this.toString() + "\n"
                + "Now you have " + currNumOfTask + " tasks in the list.\n";
        // the toString is called from the child class which is amazing => RTT is child class type i guess
    }
}

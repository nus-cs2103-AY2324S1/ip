/**
 * Contains logic related to the Task object.
 */
public class Task {
    /** Tracks the number of task objects to guarantee the assigned ID will be unique */
    private static int taskCount = 0;
    /** Unique identification number for the task */
    private final int id;
    /** The description of the task */
    private String desc;

    /**
     * Default constructor for the Task object.
     * Automatically assigns the instance's ID based on the global task count.
     *
     * @param desc Description of the task
     */
    public Task(String desc) {
        // Increments the taskCount and uses the value as the unique ID for the current task
        Task.taskCount += 1;
        id = Task.taskCount;
        this.desc = desc;
    }

    /**
     * Getter method for the global Task count.
     *
     * @return Number of Tasks initialized
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Getter method for the Task id
     *
     * @return ID of the task
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for the Task description.
     *
     * @return Description of the task
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for the Task description.
     *
     * @param desc Description of the task
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}

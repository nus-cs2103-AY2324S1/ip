enum TaskStatus {
    UNMARKED,
    MARKED
}

public class Task {
    private String name;
    private TaskStatus status;

    /**
     * Returns a new Task object with the given parameters.
     *
     * @param name - the name of the Task.
     * @param TaskStatus - the status of the Task.
     */
    Task(String name, TaskStatus status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Returns a new Task object with the given parameters and a default TaskStatus of unmarked.
     *
     * @param name - the name of the Task.
     */
    Task(String name) {
        this.name = name;
        this.status = TaskStatus.UNMARKED;
    }

    /**
     * Returns a String representation of this task.
     */
    public String toString() {
        if(this.status == TaskStatus.UNMARKED) {
            return String.format("[ ] %s", this.name);
        }
        if(this.status == TaskStatus.MARKED) {
            return String.format("[X] %s", this.name);
        }
        return "";
    }
}

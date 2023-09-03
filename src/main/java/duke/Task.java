package duke;

public class Task {

    private String task;
    private boolean isDone;


    /**
     * Constructor for Task class
     *
     * @param task the task description
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }


    /**
     * Mark the task as not done
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Get the status of the task
     *
     * @return 1 represents done and 0 represents not done
     */
    public String getStatus() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Get the type of the task: Event, ToDo or Deadline
     *
     * @param task the task to check for task type
     * @return "E", "D" or "T", which stands for Event task, todo task and deadline task respectively
     */
    public String getTaskType(Task task) {
        if (task instanceof Event) {
            return "E | " + this.task + "| " + ((Event) task).getStartDateTime() + " to " + ((Event) task).getEndDateTime();
        } else if (task instanceof Deadline) {
            return "D | " + this.task + "| " + ((Deadline) task).getTime();
        } else if (task instanceof ToDo) {
            return "T | " + this.task;
        } else {
            return "Unknown";
        }
    }

    /**
     * returns the task description
     *
     * @return task description
     */
    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X" : " ") + "] " + this.task;
    }
}
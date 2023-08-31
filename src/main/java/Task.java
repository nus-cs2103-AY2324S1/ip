/**
 * Task is the main class for tasks used by the Sidtacphi bot.
 */
public class Task {
    private String name = "";
    private boolean completed = false;

    /**
     * Constructor for the Task class.
     * 
     * @param name Name of the class
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     * 
     * @return Completion state of task and the name of task.
     */
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;     
        }
    }

    /**
     * @return Name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * @return Completed state of the task
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        completed = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmark() {
        completed = false;
    }
}

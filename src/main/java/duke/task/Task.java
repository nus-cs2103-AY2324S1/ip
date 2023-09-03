package duke.task;

/**
 * Task is the parent class that allows its Task subclass to inherit from, contains basic
 * information for a task to be registeres such as name, ogName and type
 */

public class Task {
    /**
     * field completed indicates if the task should be marked
     * field name is the name of the task name
     * field ogName is the original name of the task parsed in
     * field type is the tyoe of Task parsed in
     */
    private boolean completed;
    private final String name;
    private String type;
    private String ogName;


    /**
     * @param name name of the Task object
     */

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }
    public boolean isCompleted() {
        return this.completed;
    }


    /**
     * Method to mark the task as completed
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Method to unmark the task as not completed
     */

    public void markUncompleted() {
        this.completed = false;
    }
    public String getType() {
        return this.type;
    }
    public String getOgName() {
        return this.ogName;
    }

    /**
     * @return returns the String format of the Task class
     */
    @Override
    public String toString() {
        if (this.completed) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }
    }

}

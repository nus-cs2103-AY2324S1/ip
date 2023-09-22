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
    private boolean isCompleted;
    private final String name;
    private String type;
    private String originalName;


    /**
     * @param name name of the Task object
     */

    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }
    public boolean isCompleted() {
        return this.isCompleted;
    }


    /**
     * Method to mark the task as completed
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Method to unmark the task as not completed
     */

    public void markUncompleted() {
        this.isCompleted = false;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setOriginalName(String ogName) {
        this.originalName = ogName;
    }
    public String getOriginalName() {
        return this.originalName;
    }

    /**
     * @return returns the String format of the Task class
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Task newTask = (Task) o;
        return o.toString().equals(this.toString());
    }
}

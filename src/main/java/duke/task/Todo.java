package duke.task;

/**
 * The Todo class represents a task without a specific deadline or time.
 * It inherits from the Task class and provides methods to create and display a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task when reloading tasks from storage file.
     *
     * @param description The description of the Todo task.
     * @param status A string indicating the status of the task ("Y" for done, "N" for not done).
     */
    public Todo(String description, String status) {
        super(description);
        if(status.contains("Y")){
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }

    /**
     * Converts the task to a formatted string representation to be displayed to users.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the task to a formatted string representation for file storage.
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String toFileString(){
        return "T" + super.toFileString() ;
    }
}

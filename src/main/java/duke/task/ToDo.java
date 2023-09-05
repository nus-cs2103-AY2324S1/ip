package duke.task;

/**
 * This class inherits from the Task class and represents a ToDo task type
 * with a description and completion status
 */
public class ToDo extends Task {
    /**
     * Constructor to build a ToDo Task Object with the task description.
     *
     * @param todo The description of the ToDo Task.
     */
    public ToDo(String todo) {
        super(todo);
    }


    /**
     * Overloaded constructor to build a ToDo Task Object read from the tasks
     * saved in the txt file with the task description and completion status.
     *
     * @param todo    The description of the ToDo Task.
     * @param isDone  Represents the completion status of the task.
     */
    public ToDo(String todo, boolean isDone) {
        super(todo, isDone);
    }

    /**
     * Returns a formatted string representation of the ToDo Task, including
     * its type and description.
     *
     * @return A string representing the ToDo Task.
     */
    @Override
    public String taskString() {
        String output = "[T]" + super.taskString();
        return output;
    }

    /**
     * Returns a string representation of the ToDo Task that can be used for
     * saving the task to a txt file, including its type, completion status,
     * and description.
     *
     * @return A string suitable for saving the ToDo Task to a text file.
     */
    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "T" + "|" + status + "|" + this.description;
    }

}

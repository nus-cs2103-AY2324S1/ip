package duke.taskmanagement;
public class ToDo extends Task {

    /**
     * Constructor to create a ToDo task object
     * @param description The description of this todo task
     * @param isDone Indication of the status of this todo task,
     *               true if done and vice versa.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the status icon of whether the task is done or nor
     * @return A string that represent of status of the task, [X] if done,
     * [ ] if not done.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Returns the indication of status of the task by using
     * "1" and "0" to store it in duke.txt.
     * @return "1" if the task is done and "0" if the task is not done.
     */
    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }

    /**
     * Returns the string with the correct format to store in duke.txt
     * @return String representation of the task to store in duke.txt.
     */
    @Override
    public String saveToFileString() {
        return "T | " +  convertIsDone() + " | " + description + "\n";
    }
}

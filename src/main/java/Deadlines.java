/**
 * Represents a deadline-based task.
 * A Deadlines object is a task with a deadline represented by a description and a date.
 */
public class Deadlines extends Task {
    private final String date;

    /**
     * Initializes a new instance of the Deadlines class with a given description and date.
     *
     * @param description Description of the deadline.
     * @param date Date of the deadline.
     * @throws DukeException if the provided description or date is null or empty.
     */
    public Deadlines(String description, String date) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("description of deadline cannot be empty");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new DukeException("date of deadline cannot be empty");
        }
        this.date= date;
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Gets the type of the task.
     *
     * @return A string representing the type of the task. For Deadlines, it returns "D".
     */
    @Override
    public String getType(){
        return "D";
    }

    /**
     * Gets a formatted string representation of the Deadlines task.
     *
     * @return A string representing the Deadlines task, including its type, completion status, description, and date.
     */
@Override
    public String toString() {
        String completed = this.isCompleted() ? "[X] " : "[ ] ";
        String taskType = "[" + this.getType() + "]";
        String byMessage = "by: " + getDate();
        return  taskType + completed + this.getDescription() + "(" + byMessage + ")";
    }
}

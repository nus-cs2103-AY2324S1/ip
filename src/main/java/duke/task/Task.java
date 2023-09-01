package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a generic task in the Duke application.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    protected static final DateTimeFormatter dateTimeInputFormatter
            = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone =  false;
    }

    /**
     * Reads a task from a file and returns a corresponding Task object.
     *
     * @param line The line containing task information from the data file.
     * @return A Task object representing the task read from the file.
     */
    public static Task readFromFile(String line) {
        String[] components = line.split("\\|");
        switch(components[0]) {
            case("T"):
                return Todo.readFromFile(components);
            case("D"):
                return Deadline.readFromFile(components);
            case("E"):
                return Event.readFromFile(components);
            default:
                return null;
        }
    }

    /**
     * Returns the task in the format suitable for writing to a data file.
     *
     * @return A string in the file format representing the task.
     */
    public String writeFileFormat() {
        return (this.isDone ? "1" : "0")  + "|" + this.description;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a boolean value indicating if the tasks description contains the string s.
     *
     * @param s The String to be searched in task descripton
     * @return A true if s in task description and false if not.
     */
    public boolean contains(String s) {
        return this.description.contains(s);
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return("["
                + this.getStatusIcon()
                + "] "
                + this.description);
    }
}

package data.task;

import java.lang.StringBuilder;

public abstract class Task {
    /** The description of the task. */
    private String description;
    /** The completion of the task. */
    private boolean isDone;

    /**
     * Constructs a new Task based on the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set isDone to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Set isDone to false.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the type of task as a String.
     *
     * @return The type of task.
     */
    public abstract String getType();


    /**
     * Returns the formatted(dd/MM/yyyy HHmm) deadline date as a String.
     *
     * @return Datetime of the deadline.
     */
    public abstract String getDateTime();

    /**
     * Returns the string representation of the Task. Box will be "[ ]" if isDone is false and "[X]" if true.
     *
     * @return A string representation of this Task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    /**
     * Returns a string representation of a task that is in the format needed for storing and reading from a file.
     *
     * @return A string representation of a task in a file storing/reading format.
     */
    public String toFileString() {
        StringBuilder output = new StringBuilder();
        String type = this.getType();
        String completed;
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        String title = this.description;
        String dateTime = this.getDateTime();
        if (this.getDateTime().length() == 0) {
            output.append(type + "," + completed + "," + title + "\n");
        }
        else {
            output.append(type + "," + completed + "," + title + "," + dateTime + "\n");
        }
        return output.toString();
    }
}

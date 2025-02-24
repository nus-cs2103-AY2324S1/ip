package duke;

/**
 *  Todo class
 */
public class Todo extends Task {
    /**
     * Constructor for todo class.
     * @param description name of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The formatted string to be printed in terminal.
     * @return the formated string
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + super.description;
    }

    /**
     * The formatted string to be printed in file.
     * @return a formatted string
     */
    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "T | " + status + " | " + this.description;
    }
}

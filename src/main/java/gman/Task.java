package gman;

/**
 * Task class that Todo, Event and Deadline inherit from.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task class.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns a String representation of the Task, including the status icon and description only.
     *
     * @return a String to be printed to the user.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }


    /**
     * Returns a symbol depending on whether the Task is done.
     *
     * @return a String of a symbol.
     */
    public String getStatusIcon() { //for tasks
        return (isDone ? "[X]" : "[ ]");
    }


    /**
     * Marks an undone Task as done.
     */
    public void mark(){
        isDone = true;
        assert this.getStatusIcon().equals("[X]") : "The status icon is wrong!";

    }


    /**
     * Marks a done Task as undone.
     */
    public void unmark() {
        isDone = false;
        assert this.getStatusIcon().equals("[]") : "The status icon is wrong!";
    }


    /**
     * Reads a line in a .txt file that represents an Event task and converts it into either a Todo,
     * Deadline, or Event.
     *
     * @param line each line from the .txt file.
     * @return A Todo, Deadline or Event Task.
     */
    public static Task readFromFile(String line) {
        String[] segments = line.split(" \\| ");
        switch (segments[0]) {
            case "T":
                return Todo.readFromFile(segments);
            case "D":
                return Deadline.readFromFile(segments);
            case "E":
                return Event.readFromFile(segments);
            default:
                return null;
        }
    }


    /**
     * Returns a String to be written into the .txt file. Overridden by all child classes.
     *
     * @return A formatted String for the Task with a " | " separator.
     */
    public String stringToWrite() {
        String symbol = "O";
        if (this.isDone) {
            symbol = "X";
        }
        return (symbol + " | " + this.description);
    }


    /**
     * Returns the description of the target Task.
     * @return String description to be printed to the user.
     */
    public String getDescription() {
        return this.description;
    }

}

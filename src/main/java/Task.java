/**
 * A class for Tasks.
 */
public class Task {
    /** Class field description that describes the task. */
    protected String description;
    /** Class field isDone that tells whether the task is marked. */
    protected boolean isDone;

    /**
     * Constructor to initialize the Task class.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the status icon of the task.
     *
     * @return String "X" if task's isDone is true, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that sets the task's isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that sets the task's isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Parses a string representation of a task and creates a Task object.
     * This method takes a string representation of a task in a specific format
     * and parses it to create a corresponding Task object. The input string is
     * split using the "|" delimiter and the task type, status, description, and
     * other relevant information are extracted to create the appropriate task.
     * The created task object is then returned.
     *
     * @param line The string representation of a task to be parsed.
     * @return A Task object created from the parsed string representation.
     */
    public static Task fromString(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                String by = parts[2].trim();
                task = new Deadlines(description, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Events(description, from, to);
                break;
        }

        return task;
    }

    /**
     * Generates a string representation of the task for file storage.
     *
     * @return A formatted string representation of the task for file storage.
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return String.format("  | %s | %s", doneStatus, this.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

package tasks;

public class TodoTask extends Task {

<<<<<<< HEAD
    public TodoTask(String description) {
=======
    /**
     * Constructor for TodoTask.
     * @param description
     */
    public TodoTask (String description) {
>>>>>>> branch-A-JavaDoc
        super(description);
    }

    /**
     * Constructor for TodoTask.
     */
    public TodoTask() {
        super("");
    }
<<<<<<< HEAD
=======
    
    /**
     * Returns the string representation of the todo task.
     * @return String
     */
>>>>>>> branch-A-JavaDoc
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task to be saved in a file.
     * @return String
     */
    public String toFileString() {
        return "T | " + super.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Convert from a string to a todo task.
     * @param fileString
     */
    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.setDescription(fileStringArray[2]);
    }
}

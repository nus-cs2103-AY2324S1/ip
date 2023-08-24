public class Task {
    /**
     * the task description
     */
    protected String description;
    /**
     * variable to indicate if it is marked or not
     */
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description the string of description that would like to be stored
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * factory method to create task based on the type
     * @param type type of task
     * @param description raw string of the task
     * @return new Task object based on the types
     */
    public static Task createTaskType(String type, String description) {
        if (type.equals("deadline")) {
            String[] splitDesc = description.split(" /by ", 2);
            return new Deadline(splitDesc[0], splitDesc[1]);
        } else if (type.equals("todo")) {
            return new Todo(description);
        } else if (type.equals("event")) {
            String[] splitDesc = description.split(" /from ", 2);
            String[] splitDesc2 = splitDesc[1].split(" /to ", 2);
            return new Event(splitDesc[0], splitDesc2[0], splitDesc2[1]);
        } else {
            return new Task(description);
        }
    }

    /**
     * return the status icon
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * mark the task not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}


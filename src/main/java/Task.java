/**
 * Class For Task
 */
public abstract class Task {
    /**
     * To show whether it is marked/not
     */
    private boolean isDone;

    /**
     * The name of the task
     */
    private String name;

    /**
     * Constructor
     * @param name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Change the status to a new status
     * @param newStatus that we want to change
     */
    public void changeMarkStatus(boolean newStatus) {
        this.isDone = newStatus;
    }

    /**
     * to print it as a string
     * @return a string representing the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String getName() {
        return this.name;
    }

    public Boolean getMarkStatus() {
        return this.isDone;
    }
    public abstract String writeString();
    /**
     * To check whether the input is a task
     * @param input the task
     * @return Boolean
     * @throws DukeException
     */
    public static boolean isTask(String input) throws DukeException {
        if(input.split( " ")[0].equals("todo") || input.split( " ")[0].equals("deadline") || input.split( " ")[0].equals("event")) {
            return true;
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
        }
    }
}
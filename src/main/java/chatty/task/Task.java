package chatty.task;

/**
 * A class that handles each of the task added by the user
 */
public class Task {

    protected String task;
    protected boolean isDone;

    /**
     * Constructor that for Task class that creates one task object for each task to be added
     * @param task the task that the user wants to add
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Method that change the state of the task according to the instruction given by the user
     * @param status the status of the task that the user wants to change to
     */
    public void markStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Method that checks if the task contains the keyword entered
     * @param keyword the keyword that the user wants to find
     * @return return a boolean value to indicate if the task contains the particular keyword
     */
    public boolean checkKeyword(String keyword) {
        return this.task.contains(keyword);
    }

}

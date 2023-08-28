import java.io.Serializable;

/**
 * This class is used as the superclass to all three types o tasks that is used in the chatbot Adam
 * This class holds information such as the description of the task and whether or not someone has completed it
 */
public class Task implements Serializable {
    /**
     * This variable holds the information on what the task is
     */
    protected String description;
    /**
     * This boolean is to indicate whether this task is complete or not
     */
    protected boolean isDone;

    /**
     * This is a constructor to create a Task object
     * @param description This param gives the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * This is a toString method that has been Override to better suit the display of this class
     * @return The string form and information about this task
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * This method is used to return a String representing whether or not this task has been completed
     * @return A bracket that contains X or empty space depending on whther it is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method is used to declare that this instance of a Task has been completed
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("You have completed this task");
        System.out.println(this.toString());
    }

    /**
     * This method is used to declare that this instance of a Task has been uncompleted
     */
    public void unmarkAsDone(){
        this.isDone = false;
        System.out.println("You have unmarked this task as undone");
        System.out.println(this.toString());
    }
}

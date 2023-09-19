package duke.task;
/**
 * The Task class, which contains information pertaining to
 * Tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task.
     * @param description Description of a Task instance.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A constructor of duke.task. It is called by there is no description input.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Get the status icon to whether if the duke.task is done or not.
     * @return string String representation of whether the duke.task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Get the description of the class.
     * @return string The string representation of the class description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method prints a horizonyal line.
     */
    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /**
     * Mark the duke.task as done if it has been done.
     */
    public String markDone() {
        String strToPrint = "Nice! I've marked this duke.task as done:\n";
        this.isDone = true;
        strToPrint += ("[" + this.getStatusIcon() + "] " + this.getDescription());
        return strToPrint;
    }

    /**
     * Mark the duke.task as done without printing it.
     */
    public void markDoneNoPrint() {
        this.isDone = true;

    }

    /**
     * Mark the duke.task as undone.
     */
    public String markUndone() {
        String strToPrint = "OK, I've marked this duke.task as not done yet:\n";
        this.isDone = false;
        return strToPrint + ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }


    /**
     * Default System.out for Task.
     * @return The System.out for the class.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
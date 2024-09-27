package duke.task;

public class Task implements Comparable<Task>{
    protected String description;
    protected boolean isDone;
    public enum Prioritylist {
        HIGH, LOW, NORMAL
    }

    private Prioritylist priority = Prioritylist.NORMAL;

    @Override
    public int compareTo(Task other) {
        if ((this.priority == Prioritylist.HIGH) && (other.priority == Prioritylist.HIGH)) {
            return 0;
        } else if ((this.priority == Prioritylist.LOW) && (other.priority == Prioritylist.LOW)) {
            return 0;
        } else if ((this.priority == Prioritylist.NORMAL) && (other.priority == Prioritylist.NORMAL)) {
            return 0;
        } else if ((this.priority == Prioritylist.HIGH) && (other.priority == Prioritylist.LOW)) {
            return -1;
        } else if ((this.priority == Prioritylist.NORMAL) && (other.priority == Prioritylist.LOW)) {
            return -1;
        } else if ((this.priority == Prioritylist.HIGH) && (other.priority == Prioritylist.NORMAL)) {
            return -1;
        } else if ((this.priority == Prioritylist.LOW) && (other.priority == Prioritylist.NORMAL)) {
            return 1;
        } else if ((this.priority == Prioritylist.LOW) && (other.priority == Prioritylist.HIGH)) {
            return 1;
        } else {
            return 1;
        }
    }

    /**
     * Constructor for the Task class.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representation of whether the task us marked as done.
     * @return a String representation of whether the task us marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Edits the status of the Task.
     * @param status Boolean value that represents whether the task is marked as done or undone.
     */
    public void setStatus(boolean status) {
        isDone = status;
    }

    public void setPriority(Prioritylist priority) {
        this.priority = priority;
    }




    /**
     * Returns the String representation of the Task that is to be stored in the specified file.
     * @return The String representation of the Task that is to be stored in the specified file.
     */
    public String toWrite() {
        return "[" + getStatusIcon() + "] " + priority + " " + this.description;
    }

    /**
     * Returns the String representation of the Task that is printed.
     * @return The String representation of the Task that is printed.
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + priority + " " + this.description;
    }
}

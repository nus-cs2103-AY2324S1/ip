package duke.taskmanagement;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description The description of the task
     * @param isDone Indication whether this task is done,
     *               true if done and vice versa.
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return the status icon of whether the task is done or nor
     * @return A string that represent of status of the task, [X] if done,
     * [ ] if not done.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * To mark the status as the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * To mark the status of the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Return the String of the task as shown in list
     * @return String that consists of status of task and its description.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Return the String of the task as shown in duke.txt
     * @return String that consists of status of task and its description.
     */
    public String saveToFileString() {
        return getStatusIcon() + " | " + this.isDone + " | " + this.description;
    }

    public boolean contains(String str) {
        return this.description.contains(str);
    }
}

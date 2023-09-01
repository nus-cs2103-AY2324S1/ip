package Tasks;


public class Task {
    private String description; // the toString handles the space after the [ ] or [X]
    boolean isDone = false;
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    TaskType taskType = null;
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public TaskType taskType() {
        return this.taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStartDateTimeString() {
        return null;
    }

    public String getEndDateTimeString() {
        return null;
    }

    public String replyString (int currNumOfTask) {
        return "Got it. I've added this task:\n  "
                + this.toString() + "\n" // the toString is called from the child class which is amazing => RTT is child class type i guess
                + "Now you have " + currNumOfTask + " tasks in the list.\n";
    }
}

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String taskDesc) {
        this.description = taskDesc;
        this.isDone = false;
    }

    public void isCompleted() {
        isDone = true;
    }

    public void isNotCompleted() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getTask() {
        return "[" + getTypeIcon() + "]";
    }

    public String getTypeIcon() {
        if (this instanceof Add) {
            return "A";
        } else if (this instanceof ToDo) {
            return "T";
        } else if (this instanceof DeadLine) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        }
        return "Error: No Such Task";
    }

    @Override
    public String toString() {
        return getTask() + getStatusIcon() + " " + description;
    }
}

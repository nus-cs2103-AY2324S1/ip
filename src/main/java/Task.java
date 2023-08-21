public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return "["+ this.getStatusIcon() + "] " + this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String mark() {
        this.isDone = true;
        return "["+ this.getStatusIcon() + "] " + this.description;
    }

}

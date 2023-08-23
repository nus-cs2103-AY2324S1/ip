public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I have marked this task as done:\n" + this.toString());
    }

    public void unMark() {
        this.isDone = false;
        System.out.println("Okay! I have marked this task as not done yet\n" + this.toString());
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("You have completed this task");
        System.out.println(this.toString());
    }

    public void unmarkAsDone(){
        this.isDone = false;
        System.out.println("You have unmarked this task as undone");
        System.out.println(this.toString());
    }
}

public abstract class Task {

    private String task;
    private boolean isDone;


    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + task;
    }

    public void completeTask() {
        this.isDone = true;
        Duke.printLine();
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + this);
        Duke.printLine();
    }

    public void undoTask() {
        this.isDone = false;
        Duke.printLine();
        System.out.println("\tOk, I've marked this task as not done yet:\n\t\t" + this);
        Duke.printLine();
    }
}

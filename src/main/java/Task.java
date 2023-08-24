public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task() {
        this.task = " ";
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X" : " ") + "] " + this.task;
    }
}

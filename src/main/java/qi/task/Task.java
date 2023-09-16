package qi.task;

public class Task {
    protected String task;
    protected boolean isDone;

    protected Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isMatching(String keyWord) {
        return this.task.contains(keyWord);
    }
}

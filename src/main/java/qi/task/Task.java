package qi.task;

public class Task {
    protected String task;
    protected boolean done;

    protected Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark(boolean status) {
        this.done = status;
    }

    public boolean isMatching(String keyWord) {
        return this.task.contains(keyWord);
    }
}

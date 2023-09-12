package chatty.task;

public class Task {

    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markStatus(boolean status) {
        this.isDone = status;
    }

    public boolean checkKeyword(String keyword) {
        return this.task.contains(keyword);
    }

}

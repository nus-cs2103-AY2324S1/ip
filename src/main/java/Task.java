public class Task {

    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public void undoTask() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark = done ? "X" : " ";
        return String.format("[%s] %s", mark, this.task);
    }
}

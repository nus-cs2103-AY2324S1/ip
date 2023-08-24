public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }
}

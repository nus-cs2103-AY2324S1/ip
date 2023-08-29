public abstract class Task {

    private final String input;
    private final String name;
    private boolean isDone;

    public Task(String input, String name) {
        this.input = input;
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getTask();

    public String getTaskInput() {
        return this.input;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String checkDone() {
        return isDone ? "X" : " ";
    }
}

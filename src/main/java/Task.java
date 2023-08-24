public abstract class Task {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }
    public String getTask() {
        return String.format("[%s][T] %s", checkDone(), name);
    }

    public void markDone() {
        isDone = true;
        System.out.println(ChatterChicken.INDENT_BIG + this.getTask());
    }

    public void unmarkDone() {
        isDone = false;
        System.out.println(ChatterChicken.INDENT_BIG + this.getTask());
    }

    protected String checkDone() {
        return isDone ? "X" : " ";
    }
}

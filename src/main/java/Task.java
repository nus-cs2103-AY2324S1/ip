public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUnDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return getName();
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getSymbol() {
        return null;
    }
    public String toFileFormat() {
        // Base implementation for Task
        return getSymbol() + " | " + (isDone ? "1" : "0") + " | " + name;
    }

}

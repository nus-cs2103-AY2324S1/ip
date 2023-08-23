package task;

public class Task {
    protected String name;
    protected boolean status;

    private TaskTypes type;
    public Task() {
        this.name = "";
        this.status = false;
        this.type = null;
    }
    public Task(String name, TaskTypes type) {
        this.name = name;
        this.status = false;
        this.type = type;
    }

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }
}

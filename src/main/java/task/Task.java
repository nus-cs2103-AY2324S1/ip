package task;

public abstract class Task {
    protected String name;
    protected boolean status;

    protected static String DISCRIMINATOR = " || ";

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

    public Task(String name, TaskTypes type, boolean status) {
        this.name = name;
        this.status = status;
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

    public abstract String toSave();
}
